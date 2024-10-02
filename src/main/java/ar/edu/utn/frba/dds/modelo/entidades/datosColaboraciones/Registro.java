package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.personas.PersonaVulnerable;
import ar.edu.utn.frba.dds.modelo.entidades.utils.converters.LocalDateConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Registro {

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "persona_vulnerable_id", referencedColumnName = "id")
    private PersonaVulnerable personaVulnerable;

    @Column(name = "fecha_de_registro")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate fechaDeRegistro;
}
