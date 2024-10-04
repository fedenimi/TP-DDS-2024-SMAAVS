package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.controladores.*;
import io.javalin.Javalin;

public class Router {
    public static void init(Javalin app) {
        app.get("/", ServiceLocator.instanceOf(ControladorHome.class)::mostrarLanding);
        app.get("/registro", ServiceLocator.instanceOf(ControladorRegistro.class)::mostrarRegistro);
        app.post("/registro", ServiceLocator.instanceOf(ControladorRegistro.class)::guardarRegistro);
        app.get("/inicioSesion", ServiceLocator.instanceOf(ControladorRegistro.class)::mostrarInicioDeSesion);
        app.post("/inicioSesion", ServiceLocator.instanceOf(ControladorRegistro.class)::guardarInicioDeSesion);

        app.get("{id}/configuracion", ServiceLocator.instanceOf(ControladorColaborador.class)::edit);
        app.post("{id}/configuracion", ServiceLocator.instanceOf(ControladorColaborador.class)::update);

        app.get("/{id}/home", ServiceLocator.instanceOf(ControladorHome.class)::mostrarHome);

        app.get("{id}/donar-viandas/mapa", ServiceLocator.instanceOf(ControladorDonacionDeViandas.class)::abrirMapa);
        app.post("{id}/donar-viandas/mapa", ServiceLocator.instanceOf(ControladorDonacionDeViandas.class)::guardarMapa);
        app.get("{id}/donar-viandas/{id-heladera}", ServiceLocator.instanceOf(ControladorDonacionDeViandas.class)::index);
        app.post("{id}/donar-viandas/{id-heladera}", ServiceLocator.instanceOf(ControladorDonacionDeViandas.class)::save);

        app.get("{id}/dinero", ServiceLocator.instanceOf(ControladorDonacionDeDinero.class)::index);
        app.post("{id}/dinero", ServiceLocator.instanceOf(ControladorDonacionDeDinero.class)::save);

        app.get("{id}/distribuir-viandas", ServiceLocator.instanceOf(ControladorDistribuirViandas.class)::index);
        app.post("{id}/distribuir-viandas", ServiceLocator.instanceOf(ControladorDistribuirViandas.class)::save);
        app.get("{id}/distribuir-viandas/mapa", ServiceLocator.instanceOf(ControladorDistribuirViandas.class)::abrirMapa);
        app.post("{id}/distribuir-viandas/mapa", ServiceLocator.instanceOf(ControladorDistribuirViandas.class)::guardarMapa);

        app.get("{id}/reportar-falla", ServiceLocator.instanceOf(ControladorReportarFalla.class)::index);
        app.get("{id}/reportar-falla/mapa", ServiceLocator.instanceOf(ControladorReportarFalla.class)::abrirMapa);
        app.post("{id}/reportar-falla/mapa", ServiceLocator.instanceOf(ControladorReportarFalla.class)::guardarMapa);

        app.get("{id}/reg-persona", ServiceLocator.instanceOf(ControladorPersonaVulnerable.class)::index);
        app.post("{id}/reg-persona", ServiceLocator.instanceOf(ControladorPersonaVulnerable.class)::save);

        app.get("{id}/productos", ServiceLocator.instanceOf(ControladorProductos.class)::index);
        app.post("{id}/productos", ServiceLocator.instanceOf(ControladorProductos.class)::saveComprado);
        app.get("{id}/publicar-producto/", ServiceLocator.instanceOf(ControladorProductos.class)::create);
        app.post("{id}/publicar-producto/", ServiceLocator.instanceOf(ControladorProductos.class)::save);

        app.get("{id}/alertas", ServiceLocator.instanceOf(ControladorAlerta.class)::index);
        app.get("{id}/alertas/mapa", ServiceLocator.instanceOf(ControladorAlerta.class)::abrirMapa);

        app.get("{id}/suscripciones", ServiceLocator.instanceOf(ControladorSuscripciones.class)::index);
        app.get("{id}/suscripciones/mapa", ServiceLocator.instanceOf(ControladorSuscripciones.class)::abrirMapa);

        app.get("{id}/adminHeladeras", ServiceLocator.instanceOf(ControladorHeladeras.class)::index);
        app.post("{id}/adminHeladeras", ServiceLocator.instanceOf(ControladorHeladeras.class)::delete);
        app.get("{id}/adminHeladeras/mapa", ServiceLocator.instanceOf(ControladorHeladeras.class)::abrirMapa);

        //app.get("{id}/archivos/cargarCSV", ServiceLocator.instanceOf(ControladorCargaMasivaColaboraciones.class)::create);
        //app.post("{id}/archivos/cargarCSV", ServiceLocator.instanceOf(ControladorCargaMasivaColaboraciones.class)::save);

    }
}
