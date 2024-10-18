package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.dtos.AlertaSuscripcionDTO;
import ar.edu.utn.frba.dds.dtos.ColaboradorDTO;
import ar.edu.utn.frba.dds.dtos.HeladeraDTO;
import ar.edu.utn.frba.dds.dtos.SuscripcionHumanaDTO;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.*;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.condiciones.Desperfecto;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.condiciones.FaltanNViandas;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.condiciones.QuedanNViandas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioAlertas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;
import ar.edu.utn.frba.dds.servicios.ServiceAlertasSuscripciones;
import ar.edu.utn.frba.dds.servicios.ServiceColaboradores;
import ar.edu.utn.frba.dds.servicios.ServiceHeladeras;
import ar.edu.utn.frba.dds.servicios.ServiceSuscripcionesHumanas;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ControladorSuscripciones implements ICrudViewsHandler {
    private RepositorioHeladeras repositorioHeladeras;
    private RepositorioColaboradores repositorioColaboradores;

    public ControladorSuscripciones(RepositorioHeladeras repositorioHeladeras, RepositorioColaboradores repositorioColaboradores) {
        this.repositorioHeladeras = repositorioHeladeras;
        this.repositorioColaboradores = repositorioColaboradores;
    }
    @Override
    public void index(Context context) {
    }
    @Override
    public void show(Context context) {
        Colaborador colaborador = repositorioColaboradores.buscar(Long.valueOf(context.pathParam("id"))).get();
        ColaboradorDTO colaboradorDTO = ServiceColaboradores.toColaboradorDTO(colaborador);
        //colaboradorDTO.setAlertasSuscripcionDTO(colaborador.getAlertaSuscripciones().stream().map(ServiceAlertasSuscripciones::toAlertaSuscripcionDTO).toList());
        List<SuscripcionHumana> faltanNViandas = colaborador.getSuscripciones().stream().filter(suscripcion -> suscripcion.getTipoNotificacion().equals(TipoNotificacion.FALTAN_N_VIANDAS)).toList();
        List<SuscripcionHumana> quedanNViandas = colaborador.getSuscripciones().stream().filter(suscripcion -> suscripcion.getTipoNotificacion().equals(TipoNotificacion.QUEDAN_N_VIANDAS)).toList();
        List<SuscripcionHumana> desperfectos = colaborador.getSuscripciones().stream().filter(suscripcion -> suscripcion.getTipoNotificacion().equals(TipoNotificacion.DESPERFECTO)).toList();
        List<SuscripcionHumanaDTO> heladerasFaltan = faltanNViandas.stream().map(ServiceSuscripcionesHumanas::toSuscripcionHumanaDTO).toList();
        List<SuscripcionHumanaDTO> heladerasQuedan = quedanNViandas.stream().map(ServiceSuscripcionesHumanas::toSuscripcionHumanaDTO).toList();
        List<SuscripcionHumanaDTO> heladerasDesp = desperfectos.stream().map(ServiceSuscripcionesHumanas::toSuscripcionHumanaDTO).toList();

        Map<String, Object> model = new HashMap<>();
        model.put("colaborador", colaboradorDTO);
        model.put("heladerasFaltan", heladerasFaltan);
        model.put("heladerasQuedan", heladerasQuedan);
        model.put("heladerasDesp", heladerasDesp);
        context.render("suscripciones/suscripciones.hbs", model);

    }

    @Override
    public void create(Context context) {
        Colaborador colaborador = repositorioColaboradores.buscar(Long.valueOf(context.pathParam("id"))).get();
        Heladera heladera = repositorioHeladeras.buscar(Long.valueOf(context.formParam("id-heladera"))).get();

        if(context.formParam("faltantes") != "") {
            SuscripcionHumana suscripcion = SuscripcionHumana.builder()
                    .tipoNotificacion(TipoNotificacion.FALTAN_N_VIANDAS)
                    .heladera(heladera)
                    .cantidad(Integer.valueOf(context.formParam("faltantes")))
                    .build();
            colaborador.agregarSuscripcion(suscripcion);

            Topic topicHeladera = heladera.getTopicPorCondicion(new FaltanNViandas());
            topicHeladera.agregarSuscripcion(Suscripcion.
                    builder()
                    .suscriptor(colaborador)
                    .configurableN(Integer.valueOf(context.formParam("faltantes")))
                    .build());
        }

        if(context.formParam("restantes") != "") {
            SuscripcionHumana suscripcion = SuscripcionHumana.builder()
                    .tipoNotificacion(TipoNotificacion.QUEDAN_N_VIANDAS)
                    .heladera(heladera)
                    .cantidad(Integer.valueOf(context.formParam("restantes")))
                    .build();
            colaborador.agregarSuscripcion(suscripcion);

            Topic topicHeladera = heladera.getTopicPorCondicion(new QuedanNViandas());
            topicHeladera.agregarSuscripcion(Suscripcion.
                    builder()
                    .suscriptor(colaborador)
                    .configurableN(Integer.valueOf(context.formParam("restantes")))
                    .build());
        }

        if(context.formParam("desperfectos") != "") {
            SuscripcionHumana suscripcion = SuscripcionHumana.builder()
                    .tipoNotificacion(TipoNotificacion.DESPERFECTO)
                    .heladera(heladera)
                    .build();
            colaborador.agregarSuscripcion(suscripcion);

            Topic topicHeladera = heladera.getTopicPorCondicion(new Desperfecto());
            topicHeladera.agregarSuscripcion(Suscripcion.
                    builder()
                    .suscriptor(colaborador)
                    .build());
        }

        repositorioColaboradores.modificar(colaborador);
        repositorioHeladeras.modificar(heladera);
        context.redirect("/" + context.pathParam("id") + "/suscripciones");
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
        String id = context.formParam("suscripcion");
        context.redirect("suscripciones");
    }

    public void abrirMapa(Context context) {
        List<Heladera> heladeras = this.repositorioHeladeras.buscarTodos();
        List<HeladeraDTO> heladerasDTO = heladeras.stream().map(ServiceHeladeras::toHeladeraDTO).toList();

        Colaborador colaborador = repositorioColaboradores.buscar(Long.valueOf(context.pathParam("id"))).get();
        ColaboradorDTO colaboradorDTO = ServiceColaboradores.toColaboradorDTO(colaborador);
        Map<String, Object> model = new HashMap<>();
        model.put("heladeras", heladerasDTO);
        context.render("suscripciones/mapa/mapaSuscripciones.hbs", model);
    }
}
