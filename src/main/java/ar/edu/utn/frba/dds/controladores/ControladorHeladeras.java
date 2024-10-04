package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.dtos.ColaboradorDTO;
import ar.edu.utn.frba.dds.dtos.HeladeraDTO;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;
import ar.edu.utn.frba.dds.server.Router;
import ar.edu.utn.frba.dds.servicios.ServiceColaboradores;
import ar.edu.utn.frba.dds.servicios.ServiceHeladeras;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ControladorHeladeras implements ICrudViewsHandler{
    private RepositorioHeladeras repositorioHeladeras;

    public ControladorHeladeras(RepositorioHeladeras repositorioHeladeras) {
        this.repositorioHeladeras = repositorioHeladeras;
    }

    public void index(Context context) {
        //PRETENDE DEVOLVER UNA VISTA QUE CONTENGA A TODOS LOS PRODUCTOS ALMACENADOS EN MI SISTEMA
        List<Heladera> heladeras = this.repositorioHeladeras.buscarTodos();
        List<HeladeraDTO> heladerasDTO = heladeras.stream().
                filter(heladera -> !heladera.tieneFallas()).
                map(ServiceHeladeras::toHeladeraDTO).toList();
        List<HeladeraDTO> heladerasConFallasDTO = heladeras.stream().
                filter(Heladera::tieneFallas).
                map(ServiceHeladeras::toHeladeraDTO).toList();


        Map<String, Object> model = new HashMap<>();
        model.put("heladeras", heladerasDTO);
        model.put("heladerasFallas", heladerasConFallasDTO);
        model.put("titulo", "Listado de heladeras");

        context.render("colaboradores/adminHeladeras.hbs", model);
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
        //PRETENDE ELIMINAR UNA HELADERA DE MI SISTEMA
        String id = context.formParam("heladera");
        Optional<Heladera> heladera = this.repositorioHeladeras.buscar(Long.parseLong(id));
        if(heladera.isEmpty()){
            context.status(404);
            return;
        }
        //TODO: eliminar las que tienen alertas
        this.repositorioHeladeras.beginTransaction();
        this.repositorioHeladeras.eliminar(heladera.get());
        this.repositorioHeladeras.commitTransaction();
        context.redirect("adminHeladeras");
    }

    public void abrirMapa(Context context) {
        List<Heladera> heladeras = this.repositorioHeladeras.buscarTodos();
        List<HeladeraDTO> heladerasDTO = heladeras.stream().
                filter(heladera -> !heladera.tieneFallas()).
                map(ServiceHeladeras::toHeladeraDTO).toList();
        Map<String, Object> model = new HashMap<>();
        model.put("heladeras", heladerasDTO);
        model.put("titulo", "Listado de heladeras");
        context.render("colaboradores/mapa/mapaAdminHeladeras.hbs", model);
    }
}
