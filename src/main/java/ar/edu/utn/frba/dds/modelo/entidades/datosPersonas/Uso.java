package ar.edu.utn.frba.dds.modelo.entidades.datosPersonas;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "uso")
public class Uso {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "heladera_id")
    private Heladera heladera;
    //TODO converter
    @Getter private LocalDateTime fechaDeUso;
}
