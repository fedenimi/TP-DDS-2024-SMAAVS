package ar.edu.utn.frba.dds.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Builder
@Setter
@Getter
public class ColaboradorDTO {
    private String id;
    private String tipoColaborador;
    private String nombre;
    private String puntosDisponibles;
    private List<String> formasDeColaborar;
    private List<AlertaSuscripcionDTO> alertasSuscripcionDTO;
}
