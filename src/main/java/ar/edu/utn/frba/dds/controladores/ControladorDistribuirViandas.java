package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.dtos.HeladeraDTO;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDistribucionesViandas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioPuntuables;
import ar.edu.utn.frba.dds.servicios.ServiceHeladeras;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControladorDistribuirViandas implements ICrudViewsHandler{
    private RepositorioPuntuables repositorioPuntuables;
    private RepositorioHeladeras repositorioHeladeras;

    public ControladorDistribuirViandas(RepositorioPuntuables repositorioPuntuables, RepositorioHeladeras repositorioHeladeras) {
        this.repositorioPuntuables = repositorioPuntuables;
        this.repositorioHeladeras = repositorioHeladeras;
    }
    @Override
    public void index(Context context) {
        List<Heladera> heladeras = this.repositorioHeladeras.buscarTodos();
        List<HeladeraDTO> heladerasDTO = (List<HeladeraDTO>) heladeras.stream().
                filter(heladera -> !heladera.tieneFallas()).
                map(heladera -> ServiceHeladeras.toHeladeraDTO(heladera));
        List<HeladeraDTO> heladerasConFallasDTO = (List<HeladeraDTO>) heladeras.stream().
                filter(Heladera::tieneFallas).
                map(heladera -> ServiceHeladeras.toHeladeraDTO(heladera));
        Map<String, Object> model = new HashMap<>();
        model.put("heladeras", heladerasDTO);
        model.put("heladerasFallas", heladerasConFallasDTO);

        context.render("colaboraciones/distribuirViandas.hbs", model);
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
        System.out.println("Mapa guardado");
        System.out.println("Heladera origen: " + context.formParam("heladera-origen"));
        System.out.println("Heladera destino: " + context.formParam("heladera-destino"));
        context.redirect("home");
    }
}
