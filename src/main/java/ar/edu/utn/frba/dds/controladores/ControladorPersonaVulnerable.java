package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioPersonasVulnerables;
import io.javalin.http.Context;

public class ControladorPersonaVulnerable implements ICrudViewsHandler{
    private RepositorioPersonasVulnerables repositorioPersonasVulnerables;

    public ControladorPersonaVulnerable(RepositorioPersonasVulnerables repositorioPersonasVulnerables) {
        this.repositorioPersonasVulnerables = repositorioPersonasVulnerables;
    }
    @Override
    public void index(Context context) {
        context.render("colaboraciones/reg-persona.hbs");
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) {
        System.out.println("TipoDocumento: ");
        System.out.println(context.formParam("tipo-documento-pv"));
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
}
