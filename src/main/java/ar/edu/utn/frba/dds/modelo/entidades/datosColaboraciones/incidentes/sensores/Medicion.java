package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores;

import ar.edu.utn.frba.dds.modelo.entidades.utils.converters.LocalDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "medicion")
public class Medicion {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "temperatura")
    private Double temperatura;

    @Column(name = "fecha_y_hora")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime fechaYHora;
}
