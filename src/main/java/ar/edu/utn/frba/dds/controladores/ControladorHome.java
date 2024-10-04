package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.dtos.ColaboradorDTO;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.AlertaSuscripcion;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.TipoNotificacion;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import ar.edu.utn.frba.dds.servicios.ServiceColaboradores;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControladorHome {
    RepositorioColaboradores repositorioColaboradores;

    public ControladorHome(RepositorioColaboradores repositorioColaboradores) {
        this.repositorioColaboradores = repositorioColaboradores;
    }
    public void mostrarLanding(Context context) {
        context.render("main/landing.hbs");
    }

    public void mostrarHome(Context context) {
        Colaborador colaborador = repositorioColaboradores.buscar(Long.valueOf(context.pathParam("id"))).get();
        ColaboradorDTO colaboradorDTO = ServiceColaboradores.toColaboradorDTO(colaborador);
        List<AlertaSuscripcion> faltanNViandas = colaborador.getAlertaSuscripciones().stream().filter(alerta -> alerta.getTipoNotificacion().equals(TipoNotificacion.FALTAN_N_VIANDAS)).toList();
        List<AlertaSuscripcion> quedanNViandas = colaborador.getAlertaSuscripciones().stream().filter(alerta -> alerta.getTipoNotificacion().equals(TipoNotificacion.QUEDAN_N_VIANDAS)).toList();
        List<AlertaSuscripcion> desperfectos= colaborador.getAlertaSuscripciones().stream().filter(alerta -> alerta.getTipoNotificacion().equals(TipoNotificacion.DESPERFECTO)).toList();

        Map<String, Object> model = new HashMap<>();
        String id = context.pathParam("id");
        //Colaborador colaborador = ServiceLocator.instanceOf(RepositorioColaboradores.class).buscar(Long.parseLong(id)).get();
        //model.put("nombre", colaborador.getNombre());
        //model.put("puntos", colaborador.getPuntosDisponibles());
        model.put("colaborador", colaboradorDTO);
        model.put("nombre", "Juan");
        model.put("puntos", "300");
        context.render("main/home.hbs", model);
    }

    public void mostrarDonacionViandas(Context context) {

    }
}
