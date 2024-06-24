package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;

public class SensorMovimiento {
    private ReceptorSensorMovimiento receptor;

    public void recibirDato() {
        receptor.registrarAlerta(Estado.FRAUDE);
        receptor.agregarFraude();
    }

}
