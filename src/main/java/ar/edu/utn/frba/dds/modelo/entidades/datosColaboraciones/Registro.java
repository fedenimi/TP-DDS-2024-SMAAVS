package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.personas.PersonaVulnerable;
import ar.edu.utn.frba.dds.modelo.entidades.utils.converters.LocalDateConverter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "registro")
public class Registro {
    @Id
    @GeneratedValue
    private Long id;

    @Transient //TODO: Transient
    private PersonaVulnerable personaVulnerable;

    @Column(name = "fecha_de_registro")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate fechaDeRegistro;
}
