package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "ModeloHeladera")
public class ModeloHeladera {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nombre", columnDefinition = "VARCHAR(255)")
    private String nombre;

    @Column(name = "temperatura_minima", columnDefinition = "FLOAT(5,2)")
    @Getter private float temperaturaMinima;

    @Column(name = "temperatura_maxima", columnDefinition = "FLOAT(5,2)")
    @Getter private float temperaturaMaxima;
}
