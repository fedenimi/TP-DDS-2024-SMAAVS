package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DonacionDeDinero;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.frecuencia.Frecuencia;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioPuntuables;
import io.javalin.http.Context;

import java.time.LocalDate;

public class ControladorDonacionDeDinero implements ICrudViewsHandler{
    private RepositorioPuntuables repositorioPuntuables;
    private RepositorioColaboradores repositorioColaboradores;

    public ControladorDonacionDeDinero(RepositorioPuntuables repositorioPuntuables, RepositorioColaboradores repositorioColaboradores) {
        this.repositorioPuntuables = repositorioPuntuables;
        this.repositorioColaboradores = repositorioColaboradores;
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
        // Formulario para hacer la donación de dinero
        context.render("colaboraciones/donacionDeDinero.hbs");
    }

    @Override
    public void save(Context context) {
        // Guardar los datos de la donación de dinero
        System.out.println("Paramétros: ");
        System.out.println(context.formParam("frecuencia"));
        System.out.println(context.formParam("monto"));

        Colaborador colaborador = repositorioColaboradores.buscar(Long.valueOf(context.pathParam("id"))).get();
        DonacionDeDinero donacionDeDinero = new DonacionDeDinero();
        donacionDeDinero.setFrecuencia(Frecuencia.class.cast(context.formParam("frecuencia").toUpperCase()));
        donacionDeDinero.setMonto(Integer.parseInt(context.formParam("monto")));
        donacionDeDinero.setFechaDeInicio(LocalDate.now());
        donacionDeDinero.setColaborador(colaborador);

        repositorioPuntuables.beginTransaction();
        repositorioPuntuables.guardar(donacionDeDinero);
        repositorioPuntuables.commitTransaction();

        colaborador.agregarPuntuable(donacionDeDinero);

        context.redirect("/"+ context.pathParam("id") +"/home");

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
