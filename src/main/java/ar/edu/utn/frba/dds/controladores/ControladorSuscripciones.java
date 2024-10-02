package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.dtos.ColaboradorDTO;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.Topic;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioAlertas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;
import ar.edu.utn.frba.dds.servicios.ServiceAlertasSuscripciones;
import ar.edu.utn.frba.dds.servicios.ServiceColaboradores;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;

public class ControladorSuscripciones implements ICrudViewsHandler {
    private RepositorioHeladeras repositorioHeladeras;
    private RepositorioColaboradores repositorioColaboradores;

    public ControladorSuscripciones(RepositorioHeladeras repositorioHeladeras, RepositorioColaboradores repositorioColaboradores) {
        this.repositorioHeladeras = repositorioHeladeras;
        this.repositorioColaboradores = repositorioColaboradores;
    }
    @Override
    public void index(Context context) {
    }
    @Override
    public void show(Context context) {
        Colaborador colaborador = repositorioColaboradores.buscar(Long.valueOf(context.pathParam("id"))).get();
        ColaboradorDTO colaboradorDTO = ServiceColaboradores.toColaboradorDTO(colaborador);
        colaboradorDTO.setAlertasSuscripcionDTO(colaborador.getAlertaSuscripciones().stream().map(ServiceAlertasSuscripciones::toAlertaSuscripcionDTO).toList());
        Map<String, Object> model = new HashMap<>();
        model.put("colaborador", colaboradorDTO);
        context.render("suscripciones/suscripciones.hbs", model);
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
    }
}
