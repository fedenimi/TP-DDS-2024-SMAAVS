package ar.edu.utn.frba.dds.modelo.entidades.datosPersonas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Getter
@Entity
@Table(name = "tarjeta_persona_vulnerable")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarjetaPersonaVulnerable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
