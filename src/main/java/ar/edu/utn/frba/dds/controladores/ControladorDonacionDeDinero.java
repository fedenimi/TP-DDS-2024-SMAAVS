package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioPuntuables;
import io.javalin.http.Context;

public class ControladorDonacionDeDinero implements ICrudViewsHandler{
    private RepositorioPuntuables repositorioPuntuables;

    public ControladorDonacionDeDinero(RepositorioPuntuables repositorioPuntuables) {
        this.repositorioPuntuables = repositorioPuntuables;
    }
    @Override
    public void index(Context context) {
        context.render("colaboraciones/donarDinero.hbs");
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) {
        System.out.println("Paramétros: ");
        System.out.println(context.formParam("frecuencia"));
        System.out.println(context.formParam("monto"));
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
