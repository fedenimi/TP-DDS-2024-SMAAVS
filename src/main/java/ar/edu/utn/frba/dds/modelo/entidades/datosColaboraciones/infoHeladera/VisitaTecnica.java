package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera;

import ar.edu.utn.frba.dds.modelo.entidades.personas.Tecnico;
import ar.edu.utn.frba.dds.modelo.entidades.utils.converters.LocalDateConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "visita_tecnica")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VisitaTecnica {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "fue_solucionado",columnDefinition = "smallint")
    private Boolean fueSolucionado;
    @Column(name = "descripcion",columnDefinition = "text")
    private String descripcion;
    @Column(name = "foto",columnDefinition = "text")
    private String foto;

    @Column(name = "fecha_visita")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate fechaVisita;

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;
}
