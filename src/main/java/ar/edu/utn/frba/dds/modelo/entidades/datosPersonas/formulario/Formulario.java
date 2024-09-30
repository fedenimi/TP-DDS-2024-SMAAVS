package ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.formulario;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@NoArgsConstructor
@Entity
@Table(name = "formulario")
public class Formulario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "formulario_pregunta",
            joinColumns = @JoinColumn(name = "formulario_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pregunta_id", referencedColumnName = "id")
    )
    private List<Pregunta> preguntas;

    public Formulario(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }



}
