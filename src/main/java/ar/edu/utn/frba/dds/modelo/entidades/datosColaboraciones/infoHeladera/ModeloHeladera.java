package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ModeloHeladera")
public class ModeloHeladera {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    @Getter private float temperaturaMinima;
    @Getter private float temperaturaMaxima;
}
