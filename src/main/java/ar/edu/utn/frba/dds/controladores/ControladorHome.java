package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;

public class ControladorHome {
    public void mostrarLanding(Context context) {
        context.render("main/landing.hbs");
    }

    public void mostrarHome(Context context) {
        Map<String, Object> model = new HashMap<>();
        String id = context.pathParam("id");
        //Colaborador colaborador = ServiceLocator.instanceOf(RepositorioColaboradores.class).buscar(Long.parseLong(id)).get();
        //model.put("nombre", colaborador.getNombre());
        //model.put("puntos", colaborador.getPuntosDisponibles());
        model.put("nombre", "Juan");
        model.put("puntos", "300");
        context.render("main/home.hbs", model);
    }

    public void mostrarDonacionViandas(Context context) {
        context.render("colaboraciones/donarViandas.hbs");
    }
}
