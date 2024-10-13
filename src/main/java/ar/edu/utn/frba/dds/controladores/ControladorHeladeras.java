package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.dtos.ColaboradorDTO;
import ar.edu.utn.frba.dds.dtos.HeladeraDTO;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.HacerseCargoDeHeladera;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.Puntuable;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.Direccion;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.Punto;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.Topic;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.condiciones.Desperfecto;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.condiciones.FaltanNViandas;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.condiciones.QuedanNViandas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioPuntuables;
import ar.edu.utn.frba.dds.server.Router;
import ar.edu.utn.frba.dds.servicios.ServiceColaboradores;
import ar.edu.utn.frba.dds.servicios.ServiceHeladeras;
import io.javalin.http.Context;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class ControladorHeladeras implements ICrudViewsHandler{
    private RepositorioHeladeras repositorioHeladeras;
    private RepositorioColaboradores repositorioColaboradores;
    private RepositorioPuntuables repositorioPuntuables;

    public ControladorHeladeras(RepositorioHeladeras repositorioHeladeras, RepositorioColaboradores repositorioColaboradores, RepositorioPuntuables repositorioPuntuables) {
        this.repositorioHeladeras = repositorioHeladeras;
        this.repositorioColaboradores = repositorioColaboradores;
        this.repositorioPuntuables = repositorioPuntuables;
    }

    public void index(Context context) {
        //PRETENDE DEVOLVER UNA VISTA QUE CONTENGA A TODOS LOS PRODUCTOS ALMACENADOS EN MI SISTEMA
        List<Heladera> heladeras = this.repositorioHeladeras.buscarTodos();
        Colaborador colaborador = this.repositorioColaboradores.buscar(Long.parseLong(context.pathParam("id"))).get();
        List<HeladeraDTO> heladerasDTO = heladeras.stream().
                filter(heladera -> !heladera.tieneFallas() && colaborador.equals(ServiceHeladeras.colaboradorDe(heladera))).
                map(ServiceHeladeras::toHeladeraDTO).toList();
        List<HeladeraDTO> heladerasConFallasDTO = heladeras.stream().
                filter(heladera -> heladera.tieneFallas() && colaborador.equals(ServiceHeladeras.colaboradorDe(heladera))).
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

    public void guardarHeladera(Context context) {
        //TODO: hacelo fede, tiro goool
        Colaborador colaborador = this.repositorioColaboradores.buscar(Long.parseLong(context.pathParam("id"))).get();
        Heladera heladera = new Heladera();
        heladera.setFechaYHoraInicio(LocalDateTime.now());
        heladera.setEstado(Estado.ACTIVA);
        heladera.setAperturas(new ArrayList<>());
        heladera.setSolicitudAperturas(new ArrayList<>());
        heladera.setVisitaTecnicas(new ArrayList<>());
        heladera.setDireccion(Direccion.builder().direccion(context.formParam("direccion"))
                .punto(Punto.builder().latitud(Double.valueOf(context.formParam("latitud"))).longitud(Double.valueOf(context.formParam("longitud"))).build())
                .nombre_direccion(context.formParam("direccion"))
                .build());
        heladera.setTopics(Arrays.asList(
                Topic.builder().condicionSuscripcionHeladera(new Desperfecto()).suscripciones(new ArrayList<>()).mensaje("Hubo un desperfecto en la heladera").build(),
                Topic.builder().condicionSuscripcionHeladera(new FaltanNViandas()).suscripciones(new ArrayList<>()).mensaje("La heladera está cerca de llenarse").build(),
                Topic.builder().condicionSuscripcionHeladera(new QuedanNViandas()).suscripciones(new ArrayList<>()).mensaje("La heladera está por quedarse vacía").build()
        ));
        heladera.setStock(0);
        heladera.setCapacidad(50);

        this.repositorioHeladeras.beginTransaction();
        this.repositorioHeladeras.guardar(heladera);
        this.repositorioHeladeras.commitTransaction();

        HacerseCargoDeHeladera hacerseCargoDeHeladera = new HacerseCargoDeHeladera();
        hacerseCargoDeHeladera.setColaborador(colaborador);
        hacerseCargoDeHeladera.setHeladera(heladera);

        colaborador.agregarPuntuable(hacerseCargoDeHeladera);

        this.repositorioColaboradores.beginTransaction();
        this.repositorioColaboradores.modificar(colaborador);
        this.repositorioColaboradores.commitTransaction();

        this.repositorioPuntuables.beginTransaction();
        this.repositorioPuntuables.guardar(hacerseCargoDeHeladera);
        this.repositorioPuntuables.commitTransaction();

        context.redirect("/"+colaborador.getId()+"/adminHeladeras");
    }
}
