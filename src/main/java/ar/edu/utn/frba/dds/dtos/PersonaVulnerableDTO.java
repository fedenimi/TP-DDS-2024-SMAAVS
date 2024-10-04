package ar.edu.utn.frba.dds.dtos;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PersonaVulnerableDTO {
    private String id;
    private String nombre;
    private String domicilio;
    private String tipo_documento;
    private String num_documento;
    private String fecha_de_nacimiento;
}