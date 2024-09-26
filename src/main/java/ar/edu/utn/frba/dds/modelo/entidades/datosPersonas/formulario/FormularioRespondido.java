package ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.formulario;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "formulario_respondido")
public class FormularioRespondido {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "formulario_id")
    private Formulario formulario;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "formulario_respondido_id")
    private List<Respuesta> respuestas;
}
