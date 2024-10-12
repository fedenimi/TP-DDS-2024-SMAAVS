package ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.formulario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name= "respuesta")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "pregunta_id")
    private Pregunta pregunta;

    @Column(name = "respuesta")
    private String respuesta;
}
