package ar.edu.utn.frba.dds.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AperturaDTO {
    private String tipoDoc;
    private String doc;
    private String idHeladera;
    private String fechaSolicitud;
}
