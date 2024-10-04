package ar.edu.utn.frba.dds.dtos;

import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DistribucionDeViandas;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DistribuirViandasDTO {
    private String id;
    private String heladera_origen;
    private String heladera_destino;
    private String motivo;
    private String cantViandas;
}
