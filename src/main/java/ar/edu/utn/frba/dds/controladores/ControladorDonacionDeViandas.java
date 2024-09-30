package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DonacionDeViandas;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Vianda;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.TipoNotificacion;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioPuntuables;
import ar.edu.utn.frba.dds.servicios.ServiceTopics;
import io.javalin.http.Context;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ControladorDonacionDeViandas implements ICrudViewsHandler{
    private RepositorioPuntuables repositorioPuntuables;
    private RepositorioHeladeras repositorioHeladeras;
    private RepositorioColaboradores repositorioColaboradores;

    public ControladorDonacionDeViandas(RepositorioPuntuables repositorioPuntuables, RepositorioHeladeras repositorioHeladeras, RepositorioColaboradores repositorioColaboradores) {
        this.repositorioPuntuables = repositorioPuntuables;
        this.repositorioHeladeras = repositorioHeladeras;
        this.repositorioColaboradores = repositorioColaboradores;
    }
    @Override
    public void index(Context context) {
        context.render("colaboraciones/donarViandas.hbs");
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) {
        Heladera heladera = repositorioHeladeras.buscar(Long.parseLong(context.formParam("heladera"))).get();
        List<Vianda> viandas = null; // TODO: ver cómo recibo esto
        DonacionDeViandas donacionDeViandas = new DonacionDeViandas();
        donacionDeViandas.setViandasDonadas(viandas);
        donacionDeViandas.setFecha(LocalDate.now());

        Colaborador colaborador = repositorioColaboradores.buscar(Long.parseLong(context.pathParam("id"))).get();
        donacionDeViandas.setColaborador(colaborador);

        repositorioPuntuables.beginTransaction();
        repositorioPuntuables.guardar(donacionDeViandas);
        repositorioPuntuables.commitTransaction();

        colaborador.agregarPuntuable(donacionDeViandas);

        ServiceTopics.accionarTopic(heladera, TipoNotificacion.FALTAN_N_VIANDAS);

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
