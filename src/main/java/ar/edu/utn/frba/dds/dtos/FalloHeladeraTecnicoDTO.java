package ar.edu.utn.frba.dds.dtos;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FalloHeladeraTecnicoDTO {
    private String id;
    private String fechaYhora;
    private String visitaRealizada;
}
