package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;

public class SensorTemperatura {
    private ReceptorSensorTemperatura receptor;

    public void recibirDato(String temperatura) {
        Temperatura temp = new Temperatura(Float.parseFloat(temperatura));
        if(!receptor.evaluarTemperatura(temp)) {
            receptor.registrarAlerta(Estado.FALLA_TEMPERATURA);
        }
    }
}
