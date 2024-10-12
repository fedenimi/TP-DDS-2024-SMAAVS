package ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.formulario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "formulario_respondido")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FormularioRespondido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "formulario_id")
    private Formulario formulario;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "formulario_respondido_id")
    private List<Respuesta> respuestas;
}
