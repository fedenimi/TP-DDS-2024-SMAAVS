package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;

public class SensorTemperatura {
    private ReceptorSensorTemperatura receptor;

    public Alerta recibirDato(String temperatura) {
        Temperatura temp = new Temperatura(Float.parseFloat(temperatura));
        receptor.agregarMedicion(temperatura);
        if(!receptor.evaluarTemperatura(temp)) {
            return receptor.crearAlerta(receptor.getHeladera(), Estado.FALLA_TEMPERATURA);
        }
        else {
            return null;
        }
    }
}
