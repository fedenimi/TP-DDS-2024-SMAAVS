package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.dtos.HeladeraDTO;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DistribucionDeViandas;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Motivo;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.SolicitudApertura;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.TipoNotificacion;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.Topic;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDistribucionesViandas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioPuntuables;
import ar.edu.utn.frba.dds.servicios.ServiceHeladeras;
import ar.edu.utn.frba.dds.servicios.ServiceTopics;
import io.javalin.http.Context;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ControladorDistribuirViandas implements ICrudViewsHandler{
    private RepositorioPuntuables repositorioPuntuables;
    private RepositorioHeladeras repositorioHeladeras;
    private RepositorioColaboradores repositorioColaboradores;

    public ControladorDistribuirViandas(RepositorioPuntuables repositorioPuntuables, RepositorioHeladeras repositorioHeladeras, RepositorioColaboradores repositorioColaboradores) {
        this.repositorioPuntuables = repositorioPuntuables;
        this.repositorioHeladeras = repositorioHeladeras;
        this.repositorioColaboradores = repositorioColaboradores;
    }
    @Override
    public void index(Context context) {
        List<Heladera> heladeras = this.repositorioHeladeras.buscarTodos();
        System.out.println("Hola");
        Heladera hel = heladeras.get(0);
        List<HeladeraDTO> heladerasDTO = heladeras.stream().
                filter(heladera -> !heladera.tieneFallas()).
                map(heladera -> ServiceHeladeras.toHeladeraDTO(heladera)).toList();
        System.out.println("Heladera" + ServiceHeladeras.toHeladeraDTO(hel).getId());
        List<HeladeraDTO> heladerasConFallasDTO = heladeras.stream().
                filter(Heladera::tieneFallas).
                map(heladera -> ServiceHeladeras.toHeladeraDTO(heladera)).toList();

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
        Heladera heladeraOrigen = this.repositorioHeladeras.buscar(Long.parseLong(context.formParam("heladera-or"))).get();
        Heladera heladeraDestino = this.repositorioHeladeras.buscar(Long.parseLong(context.formParam("heladera-dest"))).get();
        // Crear la distribución de viandas
        DistribucionDeViandas distribucionDeViandas = new DistribucionDeViandas();
        distribucionDeViandas.setHeladeraOrigen(heladeraOrigen);
        distribucionDeViandas.setHeladeraDestino(heladeraDestino);
        distribucionDeViandas.setMotivoDistribucion(Motivo.valueOf(context.formParam("motivo")));
        distribucionDeViandas.setCantidadDeViandas(Integer.parseInt(context.formParam("cantidad-viandas")));
        distribucionDeViandas.setFecha(LocalDateTime.now());

        Colaborador colaborador = this.repositorioColaboradores.buscar(Long.parseLong(context.pathParam("id"))).get();
        distribucionDeViandas.setColaborador(colaborador);

        this.repositorioPuntuables.guardar(distribucionDeViandas);

        // Guardarla en el colaborador que la realizó
        colaborador.agregarPuntuable(distribucionDeViandas);

        // Le pido al service que notifique a los suscriptores de las heladeras
        ServiceTopics.accionarTopic(heladeraOrigen, TipoNotificacion.QUEDAN_N_VIANDAS);
        heladeraOrigen.agregarSolicitudApertura(SolicitudApertura.builder()
                .tarjetaColaborador(colaborador.getTarjetaColaborador())
                .fechaYHora(LocalDateTime.now())
                .build());
        heladeraOrigen.setStock(heladeraOrigen.getStock() - distribucionDeViandas.getCantidadDeViandas());
        repositorioHeladeras.modificar(heladeraOrigen);

        ServiceTopics.accionarTopic(heladeraDestino, TipoNotificacion.FALTAN_N_VIANDAS);
        heladeraDestino.agregarSolicitudApertura(SolicitudApertura.builder()
                .tarjetaColaborador(colaborador.getTarjetaColaborador())
                .fechaYHora(LocalDateTime.now())
                .build());
        heladeraDestino.setStock(heladeraDestino.getStock() + distribucionDeViandas.getCantidadDeViandas());
        repositorioHeladeras.modificar(heladeraDestino);

        context.redirect("/"+ context.pathParam("id") +"/home");
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
        List<Heladera> heladeras = this.repositorioHeladeras.buscarTodos();
        System.out.println("Hola");
        Heladera hel = heladeras.get(0);
        List<HeladeraDTO> heladerasDTO = heladeras.stream().
                filter(heladera -> !heladera.tieneFallas()).
                map(heladera -> ServiceHeladeras.toHeladeraDTO(heladera)).toList();
        System.out.println("Heladera" + ServiceHeladeras.toHeladeraDTO(hel).getId());
        List<HeladeraDTO> heladerasConFallasDTO = heladeras.stream().
                filter(Heladera::tieneFallas).
                map(heladera -> ServiceHeladeras.toHeladeraDTO(heladera)).toList();

        Map<String, Object> model = new HashMap<>();
        model.put("heladeras", heladerasDTO);
        model.put("heladerasFallas", heladerasConFallasDTO);
        context.render("colaboraciones/mapa/mapaDistribuirViandas.hbs", model);
    }

    public void guardarMapa(Context context) {
        System.out.println("Mapa guardado");
        System.out.println("Heladera origen: " + context.formParam("heladera-origen"));
        System.out.println("Heladera destino: " + context.formParam("heladera-destino"));
        context.redirect("/"+ context.pathParam("id") +"/home");
    }
}
