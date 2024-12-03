package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.dtos.RubroDTO;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.OfrecerProducto;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
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
import io.javalin.http.UploadedFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

        oferta.setNombre(context.formParam("producto"));
        oferta.setPuntajeMinimo(Double.valueOf(context.formParam("puntaje")));
        oferta.setRubro(this.repositorioDeRubros.buscar(Long.valueOf(context.formParam("id"))).get());
        UploadedFile uploadedFile = context.uploadedFile("imagen-producto");
        File file = new File("public-files/" + uploadedFile.filename());
        try {
            saveUploadedFile(uploadedFile.content(), file);
            oferta.setImagen("/img/" +uploadedFile.filename());
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
        nuevoProducto.setOferta(oferta);
        Colaborador colaborador = this.repositorioColaboradores.buscar(Long.valueOf(context.pathParam("id"))).get();
        nuevoProducto.setColaborador(colaborador);

        this.repositorioDeProductos.guardar(nuevoProducto);

        colaborador.guardarOfrecerProducto(nuevoProducto);
        repositorioColaboradores.modificar(colaborador);

        context.redirect("/"+context.pathParam("id")+"/home");
    }

    private void saveUploadedFile(InputStream uploadedFileStream, File fileToSave) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fileToSave)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = uploadedFileStream.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
    }

    public void saveComprado(Context context) {
        Colaborador colaborador = this.repositorioColaboradores.buscar(Long.valueOf(context.pathParam("id"))).get();
        OfrecerProducto producto = this.repositorioDeProductos.buscar(Long.valueOf(context.formParam("producto"))).get();
        colaborador.modificarPuntosPorCanje(producto.getOferta().getPuntajeMinimo());

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
