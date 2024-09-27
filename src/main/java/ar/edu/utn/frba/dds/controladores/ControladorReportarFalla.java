package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.dtos.HeladeraDTO;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioAlertas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioFallasTecnicas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioPuntuables;
import ar.edu.utn.frba.dds.servicios.ServiceHeladeras;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControladorReportarFalla implements ICrudViewsHandler{
    private RepositorioFallasTecnicas repositorioFallasTecnicas;
    private RepositorioHeladeras repositorioHeladeras;

    public ControladorReportarFalla(RepositorioFallasTecnicas repositorioFallasTecnicas, RepositorioHeladeras repositorioHeladeras) {
        this.repositorioFallasTecnicas = repositorioFallasTecnicas;
        this.repositorioHeladeras = repositorioHeladeras;
    }

    @Override
    public void index(Context context) {
        System.out.println("Index de reportar falla");
        List<Heladera> heladeras = this.repositorioHeladeras.buscarTodos();
        List<HeladeraDTO> heladerasDTO = (List<HeladeraDTO>) heladeras.stream().
                map(ServiceHeladeras::toHeladeraDTO);
        Map<String, Object> model = new HashMap<>();
        model.put("heladeras", heladerasDTO);
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
}
