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

    @Column(name = "temperatura_minima")
    @Getter private Double temperaturaMinima;

    @Column(name = "temperatura_maxima")
    @Getter private Double temperaturaMaxima;
}
