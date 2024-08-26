package ar.edu.utn.frba.dds.modelo.entidades.datosPersonas;

import ar.edu.utn.frba.dds.modelo.entidades.utils.converters.LocalDateConverter;
import ar.edu.utn.frba.dds.modelo.entidades.utils.converters.LocalDateTimeConverter;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tarjeta_colaborador")
public class TarjetaColaborador {
    @Id
    @GeneratedValue
    @Getter
    private String id;

    @Column(name = "fecha_emision")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime fechaEmision;
}
