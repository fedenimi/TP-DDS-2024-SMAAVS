package ar.edu.utn.frba.dds.domain.personas;

import ar.edu.utn.frba.dds.domain.datosPersonas.Documento;
import ar.edu.utn.frba.dds.domain.datosPersonas.Tarjeta;

import java.time.LocalDate;
import java.util.List;

public class PersonaVulnerable {
    private String nombre;
    private LocalDate fechaDeNacimiento;
    private LocalDate fechaDeRegistro;
    private String domicilio;
    private Documento documento;
    private List<PersonaVulnerable> menoresACargo;
    private Tarjeta tarjeta;
}
