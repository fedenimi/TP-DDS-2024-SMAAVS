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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControladorReportarFalla implements ICrudViewsHandler{
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
        System.out.println("Mapa guardado");
        System.out.println(context.formParam("descripcion"));
        System.out.println(context.formParam("imagen"));
        System.out.println(context.formParam("heladera"));

        Heladera heladera = this.repositorioHeladeras.buscar(Long.parseLong(context.formParam("heladera"))).get();

        FallaTecnica fallaTecnica = new FallaTecnica();
        fallaTecnica.setDescripcion(context.formParam("descripcion"));
        fallaTecnica.setFoto(context.formParam("imagen"));
        fallaTecnica.setHeladera(heladera);
        fallaTecnica.setReportador(this.repositorioColaboradores.buscar(Long.parseLong(context.pathParam("id"))).get());
        this.repositorioFallasTecnicas.beginTransaction();
        this.repositorioFallasTecnicas.guardar(fallaTecnica);
        this.repositorioFallasTecnicas.commitTransaction();

        RegistradorDeIncidentes.getInstance().registrarIncidente(Estado.FALLA_TECNICA, heladera, buscadorDeTecnicos);

        ServiceTopics.accionarTopic(heladera, TipoNotificacion.DESPERFECTO);
        context.redirect("/" + context.pathParam("id") + "/home");
    }
}
