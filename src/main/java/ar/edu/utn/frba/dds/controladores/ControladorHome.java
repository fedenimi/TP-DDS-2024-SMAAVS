package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.dtos.AlertaSuscripcionDTO;
import ar.edu.utn.frba.dds.dtos.ColaboradorDTO;
import ar.edu.utn.frba.dds.dtos.FalloHeladeraTecnicoDTO;
import ar.edu.utn.frba.dds.dtos.SuscripcionHumanaDTO;
import ar.edu.utn.frba.dds.modelo.entidades.acceso.Permiso;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.FallaTecnica;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.FalloHeladeraTecnico;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.RegistradorDeIncidentes;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.VisitaTecnica;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Tecnico;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.AlertaSuscripcion;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.SuscripcionHumana;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.TipoNotificacion;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioTecnicos;
import ar.edu.utn.frba.dds.servicios.*;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControladorHome {
    RepositorioColaboradores repositorioColaboradores;
    RepositorioTecnicos repositorioTecnico;
    RepositorioHeladeras repositorioHeladeras;

    public ControladorHome(RepositorioColaboradores repositorioColaboradores, RepositorioTecnicos repositorioTecnicos, RepositorioHeladeras repositorioHeladeras) {
        this.repositorioColaboradores = repositorioColaboradores;
        this.repositorioTecnico = repositorioTecnicos;
        this.repositorioHeladeras = repositorioHeladeras;
    }
    public void mostrarLanding(Context context) {
        context.render("main/landing.hbs");
    }

    public void mostrarHome(Context context) {
        List<Permiso> permisos = context.sessionAttribute("permisos");
        if (permisos.contains(Permiso.ADMIN)) {
            context.render("main/homeAdmin.hbs");
            return;
        }
        if (permisos.contains(Permiso.TECNICO)) {
            Map<String, Object> model = new HashMap<>();
            List<FalloHeladeraTecnico> fallos = repositorioTecnico.buscar(Long.valueOf(context.pathParam("id"))).get().getFalloHeladera().stream().filter(f -> !f.isVisitaRealizada()).toList();
            List<FalloHeladeraTecnicoDTO> fallosDTO = fallos.stream().map(ServiceFallaHeladeraTecnico::toFalloHeladeraTecnicoDTO).toList();
            model.put("falloHeladeras", fallosDTO);
            context.render("main/homeTecnico.hbs", model);
            return;
        }
        Colaborador colaborador = repositorioColaboradores.buscar(Long.valueOf(context.pathParam("id"))).get();
        ColaboradorDTO colaboradorDTO = ServiceColaboradores.toColaboradorDTO(colaborador);
        Map<String, Object> model = new HashMap<>();
        model.put("nombre", colaborador.getNombre());
        model.put("puntos", colaborador.getPuntosDisponibles());
        model.put("colaborador", colaboradorDTO);

        if (permisos.contains(Permiso.HUMANA)) {
            List<SuscripcionHumanaDTO> suscripcionHumanas = colaborador.getSuscripciones().stream().map(ServiceSuscripcionesHumanas::toSuscripcionHumanaDTO).toList();
            List<AlertaSuscripcionDTO> alertas = colaborador.getAlertaSuscripciones().stream().map(ServiceAlertasSuscripciones::toAlertaSuscripcionDTO).toList();
            model.put("suscripciones", suscripcionHumanas);
            model.put("alertas", alertas);
            context.render("main/homeHumana.hbs", model);
        } else if (permisos.contains(Permiso.JURIDICA)) {
            context.render("main/homeJuridica.hbs", model);
        }
    }

    public void mostrarDonacionViandas(Context context) {

    }

    public void guardarVisitaTecnica(Context context) {
        Heladera heladera = this.repositorioHeladeras.buscar(Long.parseLong(context.formParam("heladera"))).get();
        VisitaTecnica visitaTecnica = new VisitaTecnica();

        UploadedFile uploadedFile = context.uploadedFile("imagen-falla");
        File file = new File("src/main/resources/public/img/" + uploadedFile.filename());
        try {
            saveUploadedFile(uploadedFile.content(), file);
            visitaTecnica = VisitaTecnica.builder()
                    .fueSolucionado(true)
                    .fechaVisita(LocalDate.now())
                    .tecnico(this.repositorioTecnico.buscar(Long.parseLong(context.pathParam("id"))).get())
                    .descripcion(context.formParam("descripcion"))
                    .foto("/img/" + uploadedFile.filename())
                    .build();
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
        heladera.addVisita(visitaTecnica);
        repositorioHeladeras.modificar(heladera);

        Tecnico tecnico = repositorioTecnico.buscar(Long.parseLong(context.pathParam("id"))).get();
        tecnico.getFalloHeladera().stream().filter(f -> f.getId().toString().equals(context.formParam("fallo-id"))).findFirst().get().setVisitaRealizada(true);
        repositorioTecnico.modificar(tecnico);
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
