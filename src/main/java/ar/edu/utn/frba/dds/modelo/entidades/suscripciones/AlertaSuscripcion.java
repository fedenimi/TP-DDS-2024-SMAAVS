package ar.edu.utn.frba.dds.modelo.entidades.suscripciones;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Table(name= "alerta_suscripcion")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AlertaSuscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "topic_id")
    private SuscripcionHumana suscripcionHumana;

    @Column(name = "descripcion_alerta", columnDefinition = "TEXT")
    private String descripcion_alerta;
}
