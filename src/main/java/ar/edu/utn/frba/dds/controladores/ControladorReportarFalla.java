package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.dtos.HeladeraDTO;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.BuscadorDeTecnicos;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.FallaTecnica;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.IBuscadorDeTecnicos;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.RegistradorDeIncidentes;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.TipoNotificacion;
import ar.edu.utn.frba.dds.modelo.repositorios.*;
import ar.edu.utn.frba.dds.servicios.ServiceHeladeras;
import ar.edu.utn.frba.dds.servicios.ServiceTopics;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControladorReportarFalla implements ICrudViewsHandler {
    private RepositorioFallasTecnicas repositorioFallasTecnicas;
    private RepositorioHeladeras repositorioHeladeras;
    private RepositorioColaboradores repositorioColaboradores;
    private BuscadorDeTecnicos buscadorDeTecnicos;

    public ControladorReportarFalla(RepositorioFallasTecnicas repositorioFallasTecnicas, RepositorioHeladeras repositorioHeladeras, RepositorioColaboradores repositorioColaboradores, BuscadorDeTecnicos buscadorDeTecnicos) {
        this.repositorioFallasTecnicas = repositorioFallasTecnicas;
        this.repositorioHeladeras = repositorioHeladeras;
        this.repositorioColaboradores = repositorioColaboradores;
        this.buscadorDeTecnicos = buscadorDeTecnicos;
    }

    @Override
    public void index(Context context) {
        System.out.println("Index de reportar falla");
        List<Heladera> heladeras = this.repositorioHeladeras.buscarTodos();
        List<HeladeraDTO> heladerasDTO = heladeras.stream().
                map(ServiceHeladeras::toHeladeraDTO).toList();
        Map<String, Object> model = new HashMap<>();
        model.put("heladeras", heladerasDTO);
        context.render("colaboraciones/reportarFalla.hbs", model);
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) {

    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }

    @Override
    public void delete(Context context) {

    }

    public void abrirMapa(Context context) {
        List<Heladera> heladeras = this.repositorioHeladeras.buscarTodos();
        List<HeladeraDTO> heladerasDTO = heladeras.stream().
                map(ServiceHeladeras::toHeladeraDTO).toList();
        Map<String, Object> model = new HashMap<>();
        model.put("heladeras", heladerasDTO);
        context.render("colaboraciones/mapa/mapaFallas.hbs", model);
    }

    public void guardarMapa(Context context) {
        Heladera heladera = this.repositorioHeladeras.buscar(Long.parseLong(context.formParam("heladera"))).get();
        FallaTecnica fallaTecnica = new FallaTecnica();
        fallaTecnica.setDescripcion(context.formParam("descripcion"));
        UploadedFile uploadedFile = context.uploadedFile("imagen-falla");
        File file = new File("src/main/resources/public/img/" + uploadedFile.filename());
        try {
            saveUploadedFile(uploadedFile.content(), file);
            fallaTecnica.setFoto("/img/" + uploadedFile.filename());
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
        fallaTecnica.setHeladera(heladera);
        fallaTecnica.setReportador(this.repositorioColaboradores.buscar(Long.parseLong(context.pathParam("id"))).get());
        fallaTecnica.setFechaYHora(LocalDateTime.now());
        this.repositorioFallasTecnicas.guardar(fallaTecnica);

        RegistradorDeIncidentes.getInstance().registrarIncidente(Estado.FALLA_TECNICA, heladera, buscadorDeTecnicos);

        repositorioHeladeras.modificar(heladera);

        ServiceTopics.accionarTopic(heladera, TipoNotificacion.DESPERFECTO);
        context.redirect("/" + context.pathParam("id") + "/home");
    }


    private void saveUploadedFile(InputStream uploadedFileStream, File fileToSave) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fileToSave)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = uploadedFileStream.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
    }
}