package ar.edu.utn.frba.dds.modelo.entidades.datosPersonas;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;
@Getter
@Entity
@Table(name = "tarjeta_persona_vulnerable")
public class TarjetaPersonaVulnerable {
    @Id
    @Column(name = "codigo", columnDefinition = "VARCHAR(12)")
    private String codigo;

    @Column(name = "usos_maximo_base", columnDefinition = "INT")
    private Integer usosMaximoBase;

    @Column(name = "usos_maximo_por_menor", columnDefinition = "INT")
    private Integer usosMaximoPorMenor;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "tarjeta_persona_vulnerable_id")
    private List<Uso> usos;
}
