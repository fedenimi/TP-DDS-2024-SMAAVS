package ar.edu.utn.frba.dds.modelo.entidades.localizacion;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Direccion {
    private Punto punto;

    @Column(name = "direccion", columnDefinition = "TEXT")
    private String direccion;

    @Column(name = "nombre_direccion", columnDefinition = "TEXT")
    private String nombre_direccion;
}
