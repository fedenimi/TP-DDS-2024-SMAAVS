package ar.edu.utn.frba.dds.modelo.entidades.localizacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
//@Entity
//@Table(name = "punto")
@Embeddable
@Builder
public class Punto {

    @Column(name = "latitud")
    private Double latitud;
    @Column(name = "longitud")
    private Double longitud;

}
