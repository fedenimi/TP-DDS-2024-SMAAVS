package ar.edu.utn.frba.dds.servicios;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.dtos.SuscripcionHumanaDTO;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.SuscripcionHumana;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.TipoNotificacion;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;

public class ServiceSuscripcionesHumanas {

    public static SuscripcionHumanaDTO toSuscripcionHumanaDTO(SuscripcionHumana suscripcionHumana) {
        SuscripcionHumanaDTO.SuscripcionHumanaDTOBuilder suscripcionHumanaBuilderDTO = SuscripcionHumanaDTO.builder()
                .id(String.valueOf(suscripcionHumana.getId()))
                .tipoNotificacion(suscripcionHumana.getTipoNotificacion().name())
                .nombreHeladera(suscripcionHumana.getHeladera().getDireccion().getNombre_direccion())
                .direccion(suscripcionHumana.getHeladera().getDireccion().getNombre_direccion())
                .latitud(suscripcionHumana.getHeladera().getDireccion().getPunto().getLatitud().toString())
                .longitud(suscripcionHumana.getHeladera().getDireccion().getPunto().getLongitud().toString());

        if (suscripcionHumana.getCantidad() != null) {
            suscripcionHumanaBuilderDTO.cantidad(String.valueOf(suscripcionHumana.getCantidad()));
        }
        return suscripcionHumanaBuilderDTO.build();
    }
    public static SuscripcionHumana suscripcionHumanaDe(Colaborador suscriptor, Heladera heladera, TipoNotificacion tipoNotificacion) {
        return suscriptor.getSuscripciones().stream()
                .filter(suscripcion -> suscripcion.getHeladera().equals(heladera) && suscripcion.getTipoNotificacion().equals(tipoNotificacion))
                .findFirst()
                .orElse(null);
    }
}
