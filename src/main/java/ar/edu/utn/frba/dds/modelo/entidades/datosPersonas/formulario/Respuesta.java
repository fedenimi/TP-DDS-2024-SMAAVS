package ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.formulario;

import javax.persistence.*;

@Entity
@Table(name= "respuesta")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pregunta_id")
    private Pregunta pregunta;

    @Column(name = "respuesta")
    private String respuesta;
}
