package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.IncidenteDO;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;

public class SensorMovimiento {
    private ReceptorSensorMovimiento receptor;

    public Alerta recibirDato() {
        receptor.registrarIncidente(Estado.FRAUDE);
        receptor.agregarFraude();
        return receptor.crearAlerta(receptor.getHeladera(), IncidenteDO.of(Estado.FRAUDE));
    }

}
