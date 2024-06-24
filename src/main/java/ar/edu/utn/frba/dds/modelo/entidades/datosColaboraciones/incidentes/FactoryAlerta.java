package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;

import java.time.LocalDateTime;

public class FactoryAlerta {

    private static FactoryAlerta instance = null;
    public static FactoryAlerta getInstance() {
        if(instance == null)
            instance = new FactoryAlerta();
        return instance;
    }

    public Alerta crearIncidente(Heladera heladera, IncidenteDO incidenteDO) {
        return new Alerta(incidenteDO.getEstado(), LocalDateTime.now(), heladera);
    }

}
