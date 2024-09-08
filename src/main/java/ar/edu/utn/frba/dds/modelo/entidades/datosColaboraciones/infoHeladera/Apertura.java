package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera;

import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TarjetaColaborador;
import ar.edu.utn.frba.dds.modelo.entidades.utils.converters.LocalDateConverter;
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
@Table (name = "apertura")
public class Apertura {
    @Id
    @GeneratedValue
    @Getter private Long id;

    @ManyToOne
    @JoinColumn(name = "tarjeta_colaborador_id")
    private TarjetaColaborador tarjetaColaborador;

    @Column(name = "fecha_y_hora")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime fechaYHora;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="solicitud_apertura_id")
    private SolicitudApertura solicitudApertura;
}
