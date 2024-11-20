package ar.edu.utn.frba.dds.dtos;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class TecnicoDTO {
    private String id;
    private String nombre;
    private String apellido;
    private List<FalloHeladeraTecnicoDTO> fallosHeladeraTecnicoDTO;
}
