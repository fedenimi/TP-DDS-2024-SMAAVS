package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.dtos.AlertaSuscripcionDTO;
import ar.edu.utn.frba.dds.dtos.ColaboradorDTO;
import ar.edu.utn.frba.dds.dtos.SuscripcionHumanaDTO;
import ar.edu.utn.frba.dds.modelo.entidades.acceso.Permiso;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.AlertaSuscripcion;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.SuscripcionHumana;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.TipoNotificacion;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import ar.edu.utn.frba.dds.servicios.ServiceAlertasSuscripciones;
import ar.edu.utn.frba.dds.servicios.ServiceColaboradores;
import ar.edu.utn.frba.dds.servicios.ServiceSuscripcionesHumanas;
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
        List<Permiso> permisos = context.sessionAttribute("permisos");
        if (permisos.contains(Permiso.ADMIN)) {
            context.render("main/homeAdmin.hbs");
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
}
