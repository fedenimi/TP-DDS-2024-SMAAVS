package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;

import java.time.LocalDateTime;

public class FactoryFallaTecnica {

    private static FactoryFallaTecnica instance = null;
    public static FactoryFallaTecnica getInstance() {
        if(instance == null)
            instance = new FactoryFallaTecnica();
        return instance;
    }

    public FallaTecnica crearIncidente(Heladera heladera, IncidenteDO incidenteDO) {
        return new FallaTecnica(incidenteDO.getReportador(), incidenteDO.getDescripcion(),incidenteDO.getFoto(), LocalDateTime.now(), heladera);
    }

}

