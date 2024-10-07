package ar.edu.utn.frba.dds.modelo.entidades.suscripciones;

import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "suscripcion")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Suscripcion{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter private Long id;

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    private Colaborador suscriptor;

    @Column(name = "configurableN", columnDefinition = "int")
    private Integer configurableN;
}
