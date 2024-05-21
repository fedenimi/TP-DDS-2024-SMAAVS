package ar.edu.utn.frba.dds.domain.datosColaboraciones;

import java.util.List;

public class SensorMovimiento {
    private List<Alerta> alertas;

    public boolean estaEnCondiciones() {
        return alertas.get(alertas.size() - 1).isFueResuelta();
    }
}
