package ar.edu.utn.frba.dds.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PuntoDonacionCreate {
    private String nombre;
    private Double lat;
    //@JsonProperty("long")
    @SerializedName("long")
    private Double longitud;
    private String direccion;
}
