package ar.edu.utn.frba.dds.modelo.entidades.datosPersonas;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.utils.converters.LocalDateTimeConverter;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "uso")
public class Uso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "heladera_id")
    private Heladera heladera;

    @Column(name = "fecha_de_uso")
    @Convert(converter = LocalDateTimeConverter.class)
    @Getter private LocalDateTime fechaDeUso;
}
