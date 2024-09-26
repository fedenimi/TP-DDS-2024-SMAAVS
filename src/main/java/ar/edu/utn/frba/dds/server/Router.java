package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.controladores.ControladorHome;
import ar.edu.utn.frba.dds.controladores.ControladorRegistro;
import io.javalin.Javalin;

public class Router {
    public static void init(Javalin app) {
        //app.get("/prueba", ctx -> ctx.result("Hola mundo!" + ctx.queryParam("nombre")));

        app.get("/", ServiceLocator.instanceOf(ControladorHome.class)::mostrarLanding);
        app.get("/registro", ServiceLocator.instanceOf(ControladorRegistro.class)::mostrarRegistro);
        app.post("/registro", ServiceLocator.instanceOf(ControladorRegistro.class)::guardarCampos);
        app.get("/{id}/home", ServiceLocator.instanceOf(ControladorHome.class)::mostrarHome);
        app.get("{id}/viandas", ServiceLocator.instanceOf(ControladorHome.class)::mostrarDonacionViandas);

    }
}
