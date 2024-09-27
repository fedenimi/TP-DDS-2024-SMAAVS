package ar.edu.utn.frba.dds.dtos;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class HeladeraDTO {
    private String id;
    private String nombre;
    private String direccion;
    private String latitud;
    private String longitud;
    private String cantidadViandas;
    private String capacidad;
    private String estado;
}