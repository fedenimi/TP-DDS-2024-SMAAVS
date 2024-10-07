package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.controladores.*;
import ar.edu.utn.frba.dds.modelo.entidades.acceso.Permiso;
import io.javalin.Javalin;

public class Router {
    public static void init(Javalin app) {
        app.get("/", ServiceLocator.instanceOf(ControladorHome.class)::mostrarLanding);
        app.get("/registro", ServiceLocator.instanceOf(ControladorRegistro.class)::mostrarRegistro);
        app.post("/registro", ServiceLocator.instanceOf(ControladorRegistro.class)::guardarRegistro);
        app.get("/inicioSesion", ServiceLocator.instanceOf(ControladorRegistro.class)::mostrarInicioDeSesion);
        app.post("/inicioSesion", ServiceLocator.instanceOf(ControladorRegistro.class)::guardarInicioDeSesion);

        app.get("{id}/configuracion", ServiceLocator.instanceOf(ControladorColaborador.class)::edit, Permiso.HUMANA, Permiso.JURIDICA);
        app.post("{id}/configuracion", ServiceLocator.instanceOf(ControladorColaborador.class)::update, Permiso.HUMANA, Permiso.JURIDICA);

        app.get("/{id}/home", ServiceLocator.instanceOf(ControladorHome.class)::mostrarHome);

        app.get("{id}/donar-viandas/mapa", ServiceLocator.instanceOf(ControladorDonacionDeViandas.class)::abrirMapa, Permiso.DONAR_VIANDAS);
        app.post("{id}/donar-viandas/mapa", ServiceLocator.instanceOf(ControladorDonacionDeViandas.class)::guardarMapa, Permiso.DONAR_VIANDAS);
        app.get("{id}/donar-viandas/{id-heladera}", ServiceLocator.instanceOf(ControladorDonacionDeViandas.class)::index, Permiso.DONAR_VIANDAS);
        app.post("{id}/donar-viandas/{id-heladera}", ServiceLocator.instanceOf(ControladorDonacionDeViandas.class)::save, Permiso.DONAR_VIANDAS);

        app.get("{id}/dinero", ServiceLocator.instanceOf(ControladorDonacionDeDinero.class)::index, Permiso.DONAR_DINERO);
        app.post("{id}/dinero", ServiceLocator.instanceOf(ControladorDonacionDeDinero.class)::save, Permiso.DONAR_DINERO);

        app.get("{id}/distribuir-viandas", ServiceLocator.instanceOf(ControladorDistribuirViandas.class)::index, Permiso.DISTRIBUIR_VIANDAS);
        app.post("{id}/distribuir-viandas", ServiceLocator.instanceOf(ControladorDistribuirViandas.class)::save, Permiso.DISTRIBUIR_VIANDAS);
        app.get("{id}/distribuir-viandas/mapa", ServiceLocator.instanceOf(ControladorDistribuirViandas.class)::abrirMapa, Permiso.DISTRIBUIR_VIANDAS);
        app.post("{id}/distribuir-viandas/mapa", ServiceLocator.instanceOf(ControladorDistribuirViandas.class)::guardarMapa, Permiso.DISTRIBUIR_VIANDAS);

        app.get("{id}/reportar-falla", ServiceLocator.instanceOf(ControladorReportarFalla.class)::index, Permiso.HUMANA);
        app.get("{id}/reportar-falla/mapa", ServiceLocator.instanceOf(ControladorReportarFalla.class)::abrirMapa, Permiso.HUMANA);
        app.post("{id}/reportar-falla/mapa", ServiceLocator.instanceOf(ControladorReportarFalla.class)::guardarMapa, Permiso.HUMANA);

        app.get("{id}/reg-persona", ServiceLocator.instanceOf(ControladorPersonaVulnerable.class)::index, Permiso.REGISTRAR_PERSONA_VULNERABLE);
        app.post("{id}/reg-persona", ServiceLocator.instanceOf(ControladorPersonaVulnerable.class)::save, Permiso.REGISTRAR_PERSONA_VULNERABLE);

        app.get("{id}/productos", ServiceLocator.instanceOf(ControladorProductos.class)::index);
        app.post("{id}/productos", ServiceLocator.instanceOf(ControladorProductos.class)::saveComprado);
        app.get("{id}/publicar-producto/", ServiceLocator.instanceOf(ControladorProductos.class)::create, Permiso.JURIDICA);
        app.post("{id}/publicar-producto/", ServiceLocator.instanceOf(ControladorProductos.class)::save, Permiso.JURIDICA);

        app.get("{id}/alertas", ServiceLocator.instanceOf(ControladorAlertaSuscripcion.class)::index, Permiso.HUMANA);
        app.get("{id}/alertas/mapa", ServiceLocator.instanceOf(ControladorAlertaSuscripcion.class)::abrirMapa, Permiso.HUMANA);

        app.get("{id}/suscripciones", ServiceLocator.instanceOf(ControladorSuscripciones.class)::show, Permiso.HUMANA);
        app.post("{id}/suscripciones", ServiceLocator.instanceOf(ControladorSuscripciones.class)::delete, Permiso.HUMANA);
        app.get("{id}/suscripciones/mapa", ServiceLocator.instanceOf(ControladorSuscripciones.class)::abrirMapa/*, Permiso.HUMANA*/);
        app.post("{id}/suscripciones/mapa", ServiceLocator.instanceOf(ControladorSuscripciones.class)::create/*, Permiso.HUMANA*/);

        app.get("{id}/adminHeladeras", ServiceLocator.instanceOf(ControladorHeladeras.class)::index, Permiso.ADMINISTRAR_HELADERA);
        app.post("{id}/adminHeladeras", ServiceLocator.instanceOf(ControladorHeladeras.class)::delete, Permiso.ADMINISTRAR_HELADERA);
        app.get("{id}/adminHeladeras/mapa", ServiceLocator.instanceOf(ControladorHeladeras.class)::abrirMapa, Permiso.ADMINISTRAR_HELADERA);

        app.get("{id}/cargarCSV", ServiceLocator.instanceOf(ControladorCargaMasivaColaboraciones.class)::create, Permiso.ADMIN);
        app.post("{id}/cargarCSV", ServiceLocator.instanceOf(ControladorCargaMasivaColaboraciones.class)::save, Permiso.ADMIN);

        app.get("{id}/reportes", ServiceLocator.instanceOf(ControladorReportes.class)::index, Permiso.ADMIN);

    }
}
