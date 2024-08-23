package ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.formulario;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "formulario")
public class Formulario {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    @JoinColumn(name = "formulario_id")
    private List<PreguntaDeFormulario> preguntas;

    public Formulario(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }



}
