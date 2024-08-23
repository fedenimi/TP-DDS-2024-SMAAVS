package ar.edu.utn.frba.dds.modelo.entidades.localizacion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "punto")
public class Punto {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "latitud", columnDefinition = "FLOAT(9,6)")
    private float latitud;
    @Column(name = "longitud", columnDefinition = "FLOAT(9,6)")
    private float longitud;

}
