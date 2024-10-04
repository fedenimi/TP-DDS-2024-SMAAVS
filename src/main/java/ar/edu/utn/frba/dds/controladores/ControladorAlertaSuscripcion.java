package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.dtos.AlertaSuscripcionDTO;
import ar.edu.utn.frba.dds.dtos.ColaboradorDTO;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.AlertaSuscripcion;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.TipoNotificacion;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import ar.edu.utn.frba.dds.servicios.ServiceAlertasSuscripciones;
import ar.edu.utn.frba.dds.servicios.ServiceColaboradores;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControladorAlertaSuscripcion implements ICrudViewsHandler {
    private RepositorioColaboradores repositorioColaboradores;

    public ControladorAlertaSuscripcion(RepositorioColaboradores repositorioColaboradores) {
        this.repositorioColaboradores = repositorioColaboradores;
    }
    @Override
    public void index(Context context) {
        Colaborador colaborador = repositorioColaboradores.buscar(Long.valueOf(context.pathParam("id"))).get();
        ColaboradorDTO colaboradorDTO = ServiceColaboradores.toColaboradorDTO(colaborador);
        //colaboradorDTO.setAlertasSuscripcionDTO(colaborador.getAlertaSuscripciones().stream().map(ServiceAlertasSuscripciones::toAlertaSuscripcionDTO).toList());
        List<AlertaSuscripcion> faltanNViandas = colaborador.getAlertaSuscripciones().stream().filter(alerta -> alerta.getSuscripcionHumana().getTipoNotificacion().equals(TipoNotificacion.FALTAN_N_VIANDAS)).toList();
        List<AlertaSuscripcion> quedanNViandas = colaborador.getAlertaSuscripciones().stream().filter(alerta -> alerta.getSuscripcionHumana().getTipoNotificacion().equals(TipoNotificacion.QUEDAN_N_VIANDAS)).toList();
        List<AlertaSuscripcion> desperfectos= colaborador.getAlertaSuscripciones().stream().filter(alerta -> alerta.getSuscripcionHumana().getTipoNotificacion().equals(TipoNotificacion.DESPERFECTO)).toList();
        List<AlertaSuscripcionDTO> heladerasFaltan = faltanNViandas.stream().map(ServiceAlertasSuscripciones::toAlertaSuscripcionDTO).toList();
        List<AlertaSuscripcionDTO> heladerasQuedan = quedanNViandas.stream().map(ServiceAlertasSuscripciones::toAlertaSuscripcionDTO).toList();
        List<AlertaSuscripcionDTO> heladerasDesp = desperfectos.stream().map(ServiceAlertasSuscripciones::toAlertaSuscripcionDTO).toList();

        Map<String, Object> model = new HashMap<>();
        model.put("colaborador", colaboradorDTO);
        model.put("heladerasFaltan", heladerasFaltan);
        model.put("heladerasQuedan", heladerasQuedan);
        model.put("heladerasDesp", heladerasDesp);
        context.render("suscripciones/alertas.hbs", model);
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
        Colaborador colaborador = repositorioColaboradores.buscar(Long.valueOf(context.pathParam("id"))).get();
        ColaboradorDTO colaboradorDTO = ServiceColaboradores.toColaboradorDTO(colaborador);
        //colaboradorDTO.setAlertasSuscripcionDTO(colaborador.getAlertaSuscripciones().stream().map(ServiceAlertasSuscripciones::toAlertaSuscripcionDTO).toList());
        List<AlertaSuscripcion> faltanNViandas = colaborador.getAlertaSuscripciones().stream().filter(alerta -> alerta.getSuscripcionHumana().getTipoNotificacion().equals(TipoNotificacion.FALTAN_N_VIANDAS)).toList();
        List<AlertaSuscripcion> quedanNViandas = colaborador.getAlertaSuscripciones().stream().filter(alerta -> alerta.getSuscripcionHumana().getTipoNotificacion().equals(TipoNotificacion.QUEDAN_N_VIANDAS)).toList();
        List<AlertaSuscripcion> desperfectos= colaborador.getAlertaSuscripciones().stream().filter(alerta -> alerta.getSuscripcionHumana().getTipoNotificacion().equals(TipoNotificacion.DESPERFECTO)).toList();
        List<AlertaSuscripcionDTO> heladerasFaltan = faltanNViandas.stream().map(ServiceAlertasSuscripciones::toAlertaSuscripcionDTO).toList();
        List<AlertaSuscripcionDTO> heladerasQuedan = quedanNViandas.stream().map(ServiceAlertasSuscripciones::toAlertaSuscripcionDTO).toList();
        List<AlertaSuscripcionDTO> heladerasDesp = desperfectos.stream().map(ServiceAlertasSuscripciones::toAlertaSuscripcionDTO).toList();

        Map<String, Object> model = new HashMap<>();
        model.put("colaborador", colaboradorDTO);
        model.put("heladerasFaltan", heladerasFaltan);
        model.put("heladerasQuedan", heladerasQuedan);
        model.put("heladerasDesp", heladerasDesp);
        context.render("suscripciones/mapa/mapaAlertas.hbs", model);
    }
}
