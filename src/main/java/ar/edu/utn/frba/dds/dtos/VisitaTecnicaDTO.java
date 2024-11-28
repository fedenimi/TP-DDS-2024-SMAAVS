package ar.edu.utn.frba.dds.dtos;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VisitaTecnicaDTO {
    private String id;
    private String descripcion;
    private String foto;
    private String fechaVisita;
}
