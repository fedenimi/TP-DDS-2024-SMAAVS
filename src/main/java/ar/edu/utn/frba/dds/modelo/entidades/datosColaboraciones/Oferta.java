package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones;

import javax.persistence.*;

//@Entity
//@Table(name = "oferta")
@Embeddable
public class Oferta {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "puntaje_minimo", columnDefinition = "FLOAT(10,2)")
    private Float puntajeMinimo;

    @Column(name = "nombre", columnDefinition = "VARCHAR(255)")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "rubro_id")
    private Rubro rubro;
}
