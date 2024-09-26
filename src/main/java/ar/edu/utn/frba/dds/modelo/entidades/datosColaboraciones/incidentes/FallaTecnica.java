package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.utils.converters.LocalDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "falla_tecnica")
public class FallaTecnica {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name =  "colaborador_id")
    private Colaborador reportador;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "foto", columnDefinition = "VARCHAR(255)")
    private String foto;

    @Column(name = "fecha_y_hora")
    @Convert(converter = LocalDateTimeConverter.class)
    @Getter private LocalDateTime fechaYHora;

    @ManyToOne
    @JoinColumn(name = "heladera_id")
    @Getter private Heladera heladera;
}
