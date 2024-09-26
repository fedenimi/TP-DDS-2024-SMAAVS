package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.dtos.ProductoDTO;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.OfrecerProducto;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioOfrecerProductos;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorio;
import ar.edu.utn.frba.dds.servicios.ServiceProductos;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ControladorProductos implements ICrudViewsHandler{
    private RepositorioOfrecerProductos repositorioDeProductos;

    public ControladorProductos(RepositorioOfrecerProductos repositorioDeProductos) {
        this.repositorioDeProductos = repositorioDeProductos;
    }
    @Override
    public void index(Context context) {
        //PRETENDE DEVOLVER UNA VISTA QUE CONTENGA A TODOS LOS PRODUCTOS ALMACENADOS EN MI SISTEMA
        List<OfrecerProducto> productos = this.repositorioDeProductos.buscarTodos();
        List<ProductoDTO> productosDTO = (List<ProductoDTO>) productos.stream().map(ServiceProductos::toProductoDTO);


        Map<String, Object> model = new HashMap<>();
        model.put("productos", productosDTO);
        model.put("titulo", "Listado de productos");

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
        model.put("producto", ServiceProductos.toProductoDTO(posibleProductoBuscado.get()));

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

        //TODO ver bien el seteo del producto
        //nuevoProducto.setNombre(context.formParam("nombre"));
        //nuevoProducto.setPuntaje(Float.valueOf(context.formParam("puntaje")));

        this.repositorioDeProductos.guardar(nuevoProducto);
        //O BIEN LANZO UNA PANTALLA DE EXITO
        //O BIEN REDIRECCIONO AL USER A LA PANTALLA DE LISTADO DE PRODUCTOS
        context.redirect("/productos");
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
