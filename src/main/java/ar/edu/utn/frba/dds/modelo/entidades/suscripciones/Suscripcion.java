package ar.edu.utn.frba.dds.modelo.entidades.suscripciones;

import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "suscripcion")
@Getter
public class Suscripcion{
    @Id
    @GeneratedValue
    @Getter private Long id;

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    private Colaborador suscriptor;

    @Column(name = "configurableN", columnDefinition = "int")
    private Integer configurableN;
}
