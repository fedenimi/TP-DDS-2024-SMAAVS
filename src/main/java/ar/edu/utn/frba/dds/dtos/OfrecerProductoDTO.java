package ar.edu.utn.frba.dds.dtos;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OfrecerProductoDTO {
    private String nombre;
    private String puntaje;
    private String rubro;
    private String imagen;
    private String id;
}
