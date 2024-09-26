package ar.edu.utn.frba.dds.dtos;

import lombok.Builder;

@Builder
public class PersonaVulnerableDTO {
    private String id;
    private String nombre;
    private String domicilio;
    private String tipo_documento;
    private String num_documento;
    private String fecha_de_nacimiento;
}
