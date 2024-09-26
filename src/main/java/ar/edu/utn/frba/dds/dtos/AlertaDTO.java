package ar.edu.utn.frba.dds.dtos;

import lombok.Builder;

@Builder
public class AlertaDTO {
    private String id;
    private String tipo;
    private String id_heladera;
}
