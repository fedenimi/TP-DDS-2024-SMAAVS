package ar.edu.utn.frba.dds.dtos;

import lombok.Builder;

@Builder
public class AlertaSuscripcionDTO {
    private String id;
    private String tipoNotificacion;
    private String nombre_heladera;
    private String cantidad;
}
