package ar.edu.utn.frba.dds.servicios;

import ar.edu.utn.frba.dds.dtos.AlertaSuscripcionDTO;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.AlertaSuscripcion;

public class ServiceAlertasSuscripciones {
    public static AlertaSuscripcionDTO toAlertaSuscripcionDTO(AlertaSuscripcion alertaSuscripcion) {
        return AlertaSuscripcionDTO.builder()
                .id(String.valueOf(alertaSuscripcion.getId()))
                .tipoNotificacion(String.valueOf(alertaSuscripcion.getTipoNotificacion()))
                .nombre_heladera(alertaSuscripcion.getHeladera().getDireccion().getNombre_direccion())
                .cantidad(String.valueOf(alertaSuscripcion.getCantidad()))
                .build();
    }
}
