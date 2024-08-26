package ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.formulario;

import lombok.NoArgsConstructor;

import javax.persistence.*;
@NoArgsConstructor
@Entity
@Table(name = "pregunta")
public class Pregunta {
    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(name = "pregunta", columnDefinition = "TEXT")
    private String pregunta;

    public Pregunta(String pregunta) {
        this.pregunta = pregunta;
    }
}
