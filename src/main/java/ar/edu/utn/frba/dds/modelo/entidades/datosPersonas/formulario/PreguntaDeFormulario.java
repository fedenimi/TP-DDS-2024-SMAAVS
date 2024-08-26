package ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.formulario;

import javax.persistence.*;

@Entity
@Table(name = "pregunta_formulario")
public class PreguntaDeFormulario {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pregunta_id")
    private Pregunta pregunta;
}
