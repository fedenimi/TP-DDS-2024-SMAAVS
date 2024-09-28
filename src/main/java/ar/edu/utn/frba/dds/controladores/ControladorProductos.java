package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.dtos.RubroDTO;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.OfrecerProducto;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Rubro;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioOfrecerProductos;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioRubros;
import ar.edu.utn.frba.dds.servicios.ServiceProducto;
import ar.edu.utn.frba.dds.servicios.ServiceRubro;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ControladorProductos implements ICrudViewsHandler {
    private RepositorioOfrecerProductos repositorioDeProductos;
    private RepositorioRubros repositorioDeRubros;

    public ControladorProductos(RepositorioOfrecerProductos repositorioDeProductos, RepositorioRubros repositorioDeRubros) {
        this.repositorioDeProductos = repositorioDeProductos;
        this.repositorioDeRubros = repositorioDeRubros;
    }

    @Override
    public void index(Context context) {
        //PRETENDE DEVOLVER UNA VISTA QUE CONTENGA A TODOS LOS PRODUCTOS ALMACENADOS EN MI SISTEMA
        List<OfrecerProducto> productos = this.repositorioDeProductos.buscarTodos();

        List<Rubro> rubros = this.repositorioDeRubros.buscarTodos();
        List<RubroDTO> rubrosDTO = rubros.stream().map(ServiceRubro::toRubroDTO).toList();

        rubrosDTO.forEach(rubroDTO -> {
            rubroDTO.setProductos(productos.stream().
                    filter(producto ->
                            producto.getOferta().getRubro().getId().equals(Long.valueOf(rubroDTO.getId()))
                    )
                    .map(ServiceProducto::toOfrecerProductoDTO)
                    .toList());
        });

        System.out.println(rubrosDTO.get(0).getId());
        System.out.println(rubrosDTO.get(1).getId());
        System.out.println(rubrosDTO.get(0).getProductos().get(0).getNombre());

        Map<String, Object> model = new HashMap<>();
        model.put("categorias", rubrosDTO);
        context.render("productos/productos.hbs", model);
    }

    @Override
    public void show(Context context) {
        //RECIBE POR PATH PARAM EL ID DE UN PRODUCTO Y PRETENDE DEVOLVER UNA VISTA CON EL DETALLE DE ESE PRODUCTO
        Optional<OfrecerProducto> posibleProductoBuscado = this.repositorioDeProductos.buscar(Long.valueOf(context.pathParam("id")));

        //TODO
//        if(posibleProductoBuscado.isEmpty()) {
//            context.status(HttpStatus.NOT_FOUND);
//            return;
//        }

        Map<String, Object> model = new HashMap<>();
        //model.put("producto", ServiceRubro.toProductoDTO(posibleProductoBuscado.get()));

        context.render("productos/detalle_producto.hbs", model);
    }

    @Override
    public void create(Context context) {
        //PRETENDE DEVOLVER UNA VISTA CON UN FORMULARIO PARA DAR DE ALTA UN NUEVO PRODUCTO.
        context.render("productos/formulario_producto.hbs");
    }

    @Override
    public void save(Context context) {
        OfrecerProducto nuevoProducto = new OfrecerProducto();
        System.out.println("Nombre: " + context.formParam("producto"));
        System.out.println("Puntaje: " + context.formParam("puntaje"));
        //nuevoProducto.setNombre(context.formParam("producto"));
        //nuevoProducto.setPuntaje(Float.valueOf(context.formParam("puntaje")));

        //this.repositorioDeProductos.guardar(nuevoProducto);
        //O BIEN LANZO UNA PANTALLA DE EXITO
        //O BIEN REDIRECCIONO AL USER A LA PANTALLA DE LISTADO DE PRODUCTOS
        context.redirect("home");
    }

    @Override
    public void edit(Context context) {
        //PRETENDE DEVOLVER UNA VISTA CON UN FORMULARIO QUE PERMITA EDITAR AL RECURSO QUE LLEGA POR PATH PARAM
        Optional<OfrecerProducto> posibleProductoBuscado = this.repositorioDeProductos.buscar(Long.valueOf(context.pathParam("id")));

        //TODO
//        if(posibleProductoBuscado.isEmpty()) {
//            context.status(HttpStatus.NOT_FOUND);
//            return;
//        }

        Map<String, Object> model = new HashMap<>();
        model.put("producto", posibleProductoBuscado.get());
        model.put("edicion", true);

        context.render("productos/detalle_producto.hbs", model);
    }

    @Override
    public void update(Context context) {
        //TODO
    }

    @Override
    public void delete(Context context) {
        //TODO
    }
}
