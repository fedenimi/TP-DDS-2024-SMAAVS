package ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.formulario;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@NoArgsConstructor
@Entity
@Table(name = "pregunta")
@Getter
public class Pregunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "pregunta", columnDefinition = "TEXT")
    private String pregunta;

    public Pregunta(String pregunta) {
        this.pregunta = pregunta;
    }
}
