package ar.edu.utn.frba.dds.servicios;

import ar.edu.utn.frba.dds.dtos.AlertaSuscripcionDTO;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.AlertaSuscripcion;

public class ServiceAlertasSuscripciones {
    public static AlertaSuscripcionDTO toAlertaSuscripcionDTO(AlertaSuscripcion alertaSuscripcion) {
        AlertaSuscripcionDTO.AlertaSuscripcionDTOBuilder alertaSuscripcionBuilderDTO = AlertaSuscripcionDTO.builder()
                .id(String.valueOf(alertaSuscripcion.getId()))
                .tipoNotificacion(alertaSuscripcion.getSuscripcionHumana().getTipoNotificacion().name())
                .nombre_heladera(alertaSuscripcion.getSuscripcionHumana().getHeladera().getDireccion().getNombre_direccion())
                .descripcion(alertaSuscripcion.getDescripcion_alerta());

        if (alertaSuscripcion.getSuscripcionHumana().getCantidad() != null) {
            alertaSuscripcionBuilderDTO.cantidad(String.valueOf(alertaSuscripcion.getSuscripcionHumana().getCantidad()));
        }
        return alertaSuscripcionBuilderDTO.build();
    }
}
