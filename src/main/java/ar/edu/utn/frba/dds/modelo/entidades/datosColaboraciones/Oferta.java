package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//@Entity
//@Table(name = "oferta")
@Getter
@Setter
@Embeddable
public class Oferta {
    @Column(name = "puntaje_minimo")
    private Double puntajeMinimo;

    @Column(name = "nombre", columnDefinition = "VARCHAR(255)")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "rubro_id")
    private Rubro rubro;
}
