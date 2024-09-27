package ar.edu.utn.frba.dds.modelo.entidades.localizacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Direccion {
    @Embedded
    private Punto punto;

    @Column(name = "direccion", columnDefinition = "TEXT")
    private String direccion;

    @Column(name = "nombre_direccion", columnDefinition = "TEXT")
    private String nombre_direccion;
}
