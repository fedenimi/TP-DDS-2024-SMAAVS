package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.dtos.RubroDTO;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.OfrecerProducto;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Oferta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Rubro;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioOfrecerProductos;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioRubros;
import ar.edu.utn.frba.dds.server.Router;
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
    private RepositorioColaboradores repositorioColaboradores;

    public ControladorProductos(RepositorioOfrecerProductos repositorioDeProductos, RepositorioRubros repositorioDeRubros, RepositorioColaboradores repositorioColaboradores) {
        this.repositorioDeProductos = repositorioDeProductos;
        this.repositorioDeRubros = repositorioDeRubros;
        this.repositorioColaboradores = repositorioColaboradores;
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

        Map<String, Object> model = new HashMap<>();
        //model.put("producto", ServiceRubro.toProductoDTO(posibleProductoBuscado.get()));

        context.render("productos/detalle_producto.hbs", model);
    }

    @Override
    public void create(Context context) {
        //PRETENDE DEVOLVER UNA VISTA CON UN FORMULARIO PARA DAR DE ALTA UN NUEVO PRODUCTO.
        List<Rubro> rubros = this.repositorioDeRubros.buscarTodos();
        List<RubroDTO> rubrosDTO = rubros.stream().map(ServiceRubro::toRubroDTO).toList();



        Map<String, Object> model = new HashMap<>();
        model.put("rubros", rubrosDTO);
        context.render("productos/ofrecerProducto.hbs", model);
    }
    @Override
    public void save(Context context) {
        OfrecerProducto nuevoProducto = new OfrecerProducto();
        Oferta oferta = new Oferta();
        System.out.println("Nombre: " + context.formParam("producto"));
        System.out.println("Puntaje: " + context.formParam("puntaje"));
        System.out.println("Id: " + context.formParam("id"));

        oferta.setNombre(context.formParam("producto"));
        oferta.setPuntajeMinimo(Double.valueOf(context.formParam("puntaje")));
        oferta.setRubro(this.repositorioDeRubros.buscar(Long.valueOf(context.formParam("id"))).get());

        System.out.println("Id2: " + context.formParam("id"));

        nuevoProducto.setOferta(oferta);
        System.out.println("IdColab: " + context.pathParam("id"));
        Colaborador colaborador = this.repositorioColaboradores.buscar(Long.valueOf(context.pathParam("id"))).get();
        nuevoProducto.setColaborador(colaborador);
        System.out.println("Id3: " + context.formParam("id"));

        this.repositorioDeProductos.beginTransaction();
        this.repositorioDeProductos.guardar(nuevoProducto);
        this.repositorioDeProductos.commitTransaction();

        colaborador.guardarOfrecerProducto(nuevoProducto);

        context.redirect("/"+context.pathParam("id")+"/home");
    }

    public void saveComprado(Context context) {
        System.out.println("Producto: " + context.formParam("producto"));

        Colaborador colaborador = this.repositorioColaboradores.buscar(Long.valueOf(context.pathParam("id"))).get();
        System.out.println("PRODUCTO ID:" + context.formParam("producto"));
        OfrecerProducto producto = this.repositorioDeProductos.buscar(Long.valueOf(context.formParam("producto"))).get();
        colaborador.modificarPuntosPorCanje(producto.getOferta().getPuntajeMinimo());

        repositorioDeProductos.beginTransaction();
        repositorioDeProductos.eliminar(producto);
        repositorioDeProductos.commitTransaction();

        repositorioColaboradores.modificar(colaborador);

        context.redirect("/"+context.pathParam("id")+"/home");
    }

    @Override
    public void edit(Context context) {
        //PRETENDE DEVOLVER UNA VISTA CON UN FORMULARIO QUE PERMITA EDITAR AL RECURSO QUE LLEGA POR PATH PARAM
        Optional<OfrecerProducto> posibleProductoBuscado = this.repositorioDeProductos.buscar(Long.valueOf(context.pathParam("id")));


        Map<String, Object> model = new HashMap<>();
        model.put("producto", posibleProductoBuscado.get());
        model.put("edicion", true);

        context.render("productos/detalle_producto.hbs", model);
    }

    @Override
    public void update(Context context) {

    }

    @Override
    public void delete(Context context) {

    }
}
