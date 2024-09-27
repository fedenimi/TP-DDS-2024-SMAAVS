package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones;

import lombok.*;

import javax.persistence.*;

//@Entity
//@Table(name = "oferta")
@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Oferta {
    @Column(name = "puntaje_minimo")
    private Double puntajeMinimo;

    @Column(name = "nombre", columnDefinition = "VARCHAR(255)")
    private String nombre;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "rubro_id")
    private Rubro rubro;
}
