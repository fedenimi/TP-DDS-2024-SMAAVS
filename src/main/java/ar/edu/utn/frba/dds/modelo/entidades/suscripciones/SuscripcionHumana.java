package ar.edu.utn.frba.dds.modelo.entidades.suscripciones;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Table(name= "suscripcion_humana")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SuscripcionHumana {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoNotificacion tipoNotificacion;

    @ManyToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "heladera_id")
    private Heladera heladera;

    @Column(name = "cantidad", columnDefinition = "int")
    private Integer cantidad;
}

