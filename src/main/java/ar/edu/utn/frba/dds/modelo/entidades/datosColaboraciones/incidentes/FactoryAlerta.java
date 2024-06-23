package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;

import java.time.LocalDateTime;

public class FactoryAlerta {

    public Alerta crearAlerta(Estado estado, LocalDateTime fechaYhora, Heladera heladera) {
        return new Alerta(estado, fechaYhora, heladera);
    }

}
