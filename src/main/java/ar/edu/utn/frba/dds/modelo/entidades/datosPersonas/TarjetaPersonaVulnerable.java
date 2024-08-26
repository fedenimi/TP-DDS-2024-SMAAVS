package ar.edu.utn.frba.dds.modelo.entidades.datosPersonas;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;
@Getter
@Entity
@Table(name = "barrio_por_area")
public class TarjetaPersonaVulnerable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "codigo", columnDefinition = "VARCHAR[12]")
    private String codigo;
    @Column(name = "usos_maximo_base", columnDefinition = "VARCHAR")
    private Integer usosMaximoBase;
    @Column(name = "usos_maximo_por_menor", columnDefinition = "VARCHAR")
    private Integer usosMaximoPorMenor;

    @OneToMany
    @JoinColumn(name = "tarjeta_persona_vulnerable_id")
    private List<Uso> usos;
}
