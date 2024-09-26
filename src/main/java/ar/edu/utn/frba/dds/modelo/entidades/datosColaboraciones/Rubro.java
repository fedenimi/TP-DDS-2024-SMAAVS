package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones;

import lombok.Getter;

import javax.persistence.*;
@Getter
@Entity
@Table(name = "rubro")
public class Rubro {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nombre", columnDefinition = "VARCHAR(255)")
    private String nombre;
}
