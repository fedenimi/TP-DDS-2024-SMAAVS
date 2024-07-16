package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera;

import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TarjetaColaborador;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
public class Apertura {
    private TarjetaColaborador tarjetaColaborador;
    private LocalDateTime fechaYHora;
    private SolicitudApertura solicitudApertura;
}
