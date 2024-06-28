package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.IncidenteDO;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;

public class SensorMovimiento {
    private ReceptorSensorMovimiento receptor;

    public void recibirDato() {
        receptor.registrarIncidente(IncidenteDO.of(Estado.FRAUDE));
        receptor.agregarFraude();
    }

}
