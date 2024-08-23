package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.personas.PersonaVulnerable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "registro")
public class Registro {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne //TODO: OneToOne
    private PersonaVulnerable personaVulnerable;

    //TODO: converter
    private LocalDate fechaDeRegistro;
}
