package ar.edu.utn.frba.dds.dtos;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SuscripcionHumanaDTO {
    private String id;
    private String tipoNotificacion;
    private String nombreHeladera;
    private String cantidad;
    private String direccion;
    private String latitud;
    private String longitud;
}
