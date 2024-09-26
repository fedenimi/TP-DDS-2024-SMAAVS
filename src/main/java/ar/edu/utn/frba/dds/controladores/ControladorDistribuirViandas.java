package ar.edu.utn.frba.dds.controladores;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDistribucionesViandas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioPuntuables;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;

public class ControladorDistribuirViandas implements ICrudViewsHandler{
    private RepositorioDistribucionesViandas repositorioDistribucionesViandas;

    public ControladorDistribuirViandas(RepositorioDistribucionesViandas repositorioDistribucionesViandas) {
        this.repositorioDistribucionesViandas = repositorioDistribucionesViandas;
    }
    @Override
    public void index(Context context) {
        context.render("colaboraciones/distribuirViandas.hbs");
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) {
        System.out.println("Distribuir viandas: ");
        System.out.println("Heladera origen: " + context.formParam("heladera-or"));
        System.out.println("Heladera destino: " + context.formParam("heladera-dest"));
        System.out.println("Motivo: " + context.formParam("motivo"));
        System.out.println("Cantidad de viandas: " + context.formParam("cantidad-viandas"));
        context.redirect("home");
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
        context.render("colaboraciones/mapa/mapaDistribuirViandas.hbs");
    }

    public void guardarMapa(Context context) {
        System.out.println("Distribuir viandas: ");
        System.out.println("Heladera origen: " + context.formParam("heladera-or"));
        System.out.println("Heladera destino: " + context.formParam("heladera-dest"));
        System.out.println("Motivo: " + context.formParam("motivo"));
        System.out.println("Cantidad de viandas: " + context.formParam("cantidad-viandas"));
        context.redirect("/"+context.pathParam("id")+"/home");
    }
}
