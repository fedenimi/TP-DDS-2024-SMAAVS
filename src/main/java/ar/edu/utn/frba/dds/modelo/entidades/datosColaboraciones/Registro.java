package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.personas.PersonaVulnerable;
import ar.edu.utn.frba.dds.modelo.entidades.utils.converters.LocalDateConverter;

import javax.persistence.*;
import java.time.LocalDate;

@Embeddable
public class Registro {

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private PersonaVulnerable personaVulnerable;

    @Column(name = "fecha_de_registro")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate fechaDeRegistro;
}
