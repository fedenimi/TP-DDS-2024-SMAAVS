package ar.edu.utn.frba.dds.modelo.entidades.datosPersonas;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Apertura;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.utils.converters.LocalDateConverter;
import ar.edu.utn.frba.dds.modelo.entidades.utils.converters.LocalDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table (name = "tarjeta_colaborador")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarjetaColaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_emision_tarjeta")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime fechaEmision;

    @Column(name = "codigo_alfanumerico", columnDefinition = "VARCHAR(11)")
    private String codigoAlfanumerico;

}
