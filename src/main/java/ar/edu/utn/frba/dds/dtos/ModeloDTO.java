package ar.edu.utn.frba.dds.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class ModeloDTO {
    private String nombre;
    private String temperaturaMinima;
    private String temperaturaMaxima;
    private String id;
}
