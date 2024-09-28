package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioAlertas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;
import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;

public class ControladorAlerta implements ICrudViewsHandler {
    private RepositorioAlertas repositorioAlertas;

    public ControladorAlerta(RepositorioAlertas repositorioAlertas) {
        this.repositorioAlertas = repositorioAlertas;
    }
    @Override
    public void index(Context context) {
        context.render("suscripciones/alertas.hbs");
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
        context.render("suscripciones/mapa/mapaAlertas.hbs");
    }
}
