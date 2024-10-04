package ar.edu.utn.frba.dds.servicios;

import ar.edu.utn.frba.dds.dtos.AlertaSuscripcionDTO;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.AlertaSuscripcion;

public class ServiceAlertasSuscripciones {
    public static AlertaSuscripcionDTO toAlertaSuscripcionDTO(AlertaSuscripcion alertaSuscripcion) {
        AlertaSuscripcionDTO.AlertaSuscripcionDTOBuilder alertaSuscripcionBuilderDTO = AlertaSuscripcionDTO.builder()
                .id(String.valueOf(alertaSuscripcion.getId()))
                .tipoNotificacion(alertaSuscripcion.getSuscripcionHumana().getTipoNotificacion().name())
                .nombreHeladera(alertaSuscripcion.getSuscripcionHumana().getHeladera().getDireccion().getNombre_direccion())
                .descripcion(alertaSuscripcion.getDescripcion_alerta())
                .direccion(alertaSuscripcion.getSuscripcionHumana().getHeladera().getDireccion().getDireccion())
                .latitud(String.valueOf(alertaSuscripcion.getSuscripcionHumana().getHeladera().getDireccion().getPunto().getLatitud()))
                .longitud(String.valueOf(alertaSuscripcion.getSuscripcionHumana().getHeladera().getDireccion().getPunto().getLongitud()));

        if (alertaSuscripcion.getSuscripcionHumana().getCantidad() != null) {
            alertaSuscripcionBuilderDTO.cantidad(String.valueOf(alertaSuscripcion.getSuscripcionHumana().getCantidad()));
        }
        return alertaSuscripcionBuilderDTO.build();
    }
}
