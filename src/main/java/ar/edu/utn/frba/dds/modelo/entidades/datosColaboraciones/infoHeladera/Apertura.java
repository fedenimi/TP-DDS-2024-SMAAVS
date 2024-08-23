package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera;

import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TarjetaColaborador;
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
    @JoinColumn(name="id")
    private TarjetaColaborador tarjetaColaborador;

    //TODO: converter
    private LocalDateTime fechaYHora;

    @OneToOne
    private SolicitudApertura solicitudApertura;
}
