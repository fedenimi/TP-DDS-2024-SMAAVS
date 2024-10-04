package ar.edu.utn.frba.dds.dtos;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AlertaDTO {
    private String id;
    private String tipo;
    private String id_heladera;
}
