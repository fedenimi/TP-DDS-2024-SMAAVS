package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioAlertas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioFallasTecnicas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioPuntuables;
import io.javalin.http.Context;

public class ControladorReportarFalla implements ICrudViewsHandler{
    private RepositorioFallasTecnicas repositorioFallasTecnicas;

    public ControladorReportarFalla(RepositorioFallasTecnicas repositorioFallasTecnicas) {
        this.repositorioFallasTecnicas = repositorioFallasTecnicas;
    }

    @Override
    public void index(Context context) {
        context.render("colaboraciones/reportarFalla.hbs");
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
        context.render("colaboraciones/mapa/mapaFallas.hbs");
    }

    public void guardarMapa(Context context) {
        System.out.println("Mapa guardado");
        System.out.println(context.formParam("descripcion"));
        System.out.println(context.formParam("imagen"));
        context.redirect("/" + context.pathParam("id") + "/home");
    }
}
