package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
public class Alerta {
    private Estado tipoAlerta;
    private LocalDateTime fechaYHora;
    private Heladera heladera;
}
