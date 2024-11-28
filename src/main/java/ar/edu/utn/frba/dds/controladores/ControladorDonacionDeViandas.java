package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.dtos.HeladeraDTO;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DonacionDeViandas;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Vianda;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.SolicitudApertura;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.Suscripcion;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.TipoNotificacion;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioPuntuables;
import ar.edu.utn.frba.dds.servicios.ServiceHeladeras;
import ar.edu.utn.frba.dds.servicios.ServiceTopics;
import io.javalin.http.Context;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControladorDonacionDeViandas implements ICrudViewsHandler{
    private RepositorioPuntuables repositorioPuntuables;
    private RepositorioHeladeras repositorioHeladeras;
    private RepositorioColaboradores repositorioColaboradores;

    public ControladorDonacionDeViandas(RepositorioPuntuables repositorioPuntuables, RepositorioHeladeras repositorioHeladeras, RepositorioColaboradores repositorioColaboradores) {
        this.repositorioPuntuables = repositorioPuntuables;
        this.repositorioHeladeras = repositorioHeladeras;
        this.repositorioColaboradores = repositorioColaboradores;
    }
    @Override
    public void index(Context context) {
        Heladera heladera = this.repositorioHeladeras.buscar(Long.parseLong(context.pathParam("id-heladera"))).get();
        Map<String, Object> model = new HashMap<>();
        model.put("heladera", heladera.getDireccion().getNombre_direccion());
        model.put("id", heladera.getId());
        context.render("colaboraciones/donarViandas.hbs", model);
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) {
        Heladera heladera = repositorioHeladeras.buscar(Long.parseLong(context.formParam("heladera"))).get();
        List<Vianda> viandas = new ArrayList<>();

        for(int i = 0; i < Integer.parseInt(context.formParam("cant-viandas")); i++){
            Vianda vianda = new Vianda();
            vianda.setPeso(Integer.parseInt(context.formParam("vianda["+i+"][peso]")));
            vianda.setFechaCaducidad(LocalDate.parse(context.formParam("vianda["+i+"][fecha_caducidad]")));
            vianda.setComida(context.formParam("vianda["+i+"][comida]"));
            vianda.setCalorias(Integer.parseInt(context.formParam("vianda["+i+"][calorias]")));
            vianda.setEntregada(false);
            viandas.add(vianda);
        }

        DonacionDeViandas donacionDeViandas = new DonacionDeViandas();
        donacionDeViandas.setViandasDonadas(viandas);
        donacionDeViandas.setFecha(LocalDateTime.now());

        Colaborador colaborador = repositorioColaboradores.buscar(Long.parseLong(context.pathParam("id"))).get();
        donacionDeViandas.setColaborador(colaborador);
        SolicitudApertura solicitudApertura = SolicitudApertura.builder()
                .tarjetaColaborador(colaborador.getTarjetaColaborador())
                .fechaYHora(LocalDateTime.now())
                .build();
        donacionDeViandas.setSolicitudApertura(solicitudApertura);
        heladera.agregarSolicitudApertura(solicitudApertura);

        repositorioPuntuables.guardar(donacionDeViandas);
        colaborador.agregarPuntuable(donacionDeViandas);
        repositorioColaboradores.modificar(colaborador);

        ServiceTopics.accionarTopic(heladera, TipoNotificacion.FALTAN_N_VIANDAS);
        heladera.agregarSolicitudApertura(SolicitudApertura.builder()
                .tarjetaColaborador(colaborador.getTarjetaColaborador())
                .fechaYHora(LocalDateTime.now())
                .build());
        heladera.setStock(heladera.getStock() + viandas.size());
        repositorioHeladeras.modificar(heladera);

        context.redirect("/"+context.pathParam("id")+"/home");
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
        //PRETENDE DEVOLVER UNA VISTA QUE CONTENGA A TODOS LOS PRODUCTOS ALMACENADOS EN MI SISTEMA
        List<Heladera> heladeras = this.repositorioHeladeras.buscarTodos();
        List<HeladeraDTO> heladerasDTO = heladeras.stream().
                filter(heladera -> !heladera.tieneFallas()).
                map(heladera -> ServiceHeladeras.toHeladeraDTO(heladera)).toList();
        Map<String, Object> model = new HashMap<>();
        model.put("heladeras", heladerasDTO);
        context.render("colaboraciones/mapa/mapaDonarViandas.hbs", model);
    }
    public void guardarMapa(Context context) {
        context.redirect(context.formParam("heladera"));
    }
}
