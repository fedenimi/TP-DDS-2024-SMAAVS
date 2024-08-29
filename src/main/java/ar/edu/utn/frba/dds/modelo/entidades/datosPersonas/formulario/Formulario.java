package ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.formulario;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@NoArgsConstructor
@Entity
@Table(name = "formulario")
public class Formulario {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "formulario_id")
    private List<PreguntaDeFormulario> preguntas;

    public Formulario(List<PreguntaDeFormulario> preguntas) {
        this.preguntas = preguntas;
    }



}
