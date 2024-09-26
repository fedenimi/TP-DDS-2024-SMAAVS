package ar.edu.utn.frba.dds.dtos;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class FallaTecnicaDTO {
    private String doc;
    private String tipoDoc;
    private String descripcion;
    private String foto;
    private String fechaYHora;
    private String idHeladera;
}
