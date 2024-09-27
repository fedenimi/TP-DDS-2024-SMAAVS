package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.dtos.ColaboradorDTO;
import ar.edu.utn.frba.dds.dtos.ProductoDTO;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.OfrecerProducto;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioOfrecerProductos;
import ar.edu.utn.frba.dds.server.Router;
import ar.edu.utn.frba.dds.servicios.ServiceColaboradores;
import ar.edu.utn.frba.dds.servicios.ServiceProductos;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ControladorColaborador implements ICrudViewsHandler {
    private RepositorioColaboradores repositorioColaboradores;

    public ControladorColaborador(RepositorioColaboradores repositorioColaboradores) {
        this.repositorioColaboradores = repositorioColaboradores;
    }

    @Override
    public void index(Context context) {
        //PRETENDE DEVOLVER UNA VISTA QUE CONTENGA A TODOS LOS PRODUCTOS ALMACENADOS EN MI SISTEMA
        List<Colaborador> colaboradores = this.repositorioColaboradores.buscarTodos();
        List<ColaboradorDTO> colaboradoresDTO = (List<ColaboradorDTO>) colaboradores.stream().map(ServiceColaboradores::toColaboradorDTO);


        Map<String, Object> model = new HashMap<>();
        model.put("colaboradores", colaboradoresDTO);
        model.put("titulo", "Listado de productos");

        context.render("colaboradores/colaboradores.hbs", model);
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {
        // Formulario para modificar la configuración de un colaborador
        context.render("colaboradores/configuracion.hbs");
    }

    @Override
    public void save(Context context) {

    }

    @Override
    public void edit(Context context) {
        context.render("colaboradores/configuracion.hbs");
    }

    @Override
    public void update(Context context) {

        System.out.println("Actualizando colaborador");
        System.out.println(context.formParam("donar-dinero"));
        System.out.println(context.formParam("donar-viandas"));
        System.out.println(context.formParam("distribuir-viandas"));
       /*
        // Guardar lo que se modificó en el formulario de configuración
        Optional<Colaborador> posibleColaborador = this.repositorioColaboradores.buscar(Long.valueOf(context.pathParam("id")));

        //TODO
//        if(posibleProductoBuscado.isEmpty()) {
//            context.status(HttpStatus.NOT_FOUND);
//            return;
//        }
        Colaborador colaborador = posibleColaborador.get();
        // TODO: Setear los atributos del colaborador con los valores del formulario
        repositorioColaboradores.guardar(colaborador);*/
        context.redirect("/"+context.pathParam("id") +"/home");
    }

    @Override
    public void delete(Context context) {

    }
}
