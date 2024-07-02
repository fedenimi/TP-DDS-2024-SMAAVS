package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.IncidenteDO;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;

public class SensorTemperatura {
    private ReceptorSensorTemperatura receptor;

    public Alerta recibirDato(String temperatura) {
        Temperatura temp = new Temperatura(Float.parseFloat(temperatura));
        receptor.agregarMedicion(temperatura);
        if(!receptor.evaluarTemperatura(temp)) {
            receptor.registrarIncidente(Estado.FALLA_TEMPERATURA);
            return receptor.crearAlerta(receptor.getHeladera(), IncidenteDO.of(Estado.FALLA_TEMPERATURA));
        }
        else {
            return null;
        }
    }
}
