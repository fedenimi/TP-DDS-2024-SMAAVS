package ar.edu.utn.frba.dds.dtos;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import lombok.Builder;

@Builder
public class HeladeraDTO {
    private String id;
    private String direccion;
    private String cantViandas;
    private String capacidad;
}
