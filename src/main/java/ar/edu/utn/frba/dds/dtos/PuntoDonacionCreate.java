package ar.edu.utn.frba.dds.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PuntoDonacionCreate {
    private String nombre;
    private Double lat;
    @JsonProperty("long")
    private Double longitud;
    private String direccion;
}
