package ar.edu.utn.frba.dds.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductoDTO {
    private String id;
    private String puntaje;
    private String nombre;
    private String rubro;
}
