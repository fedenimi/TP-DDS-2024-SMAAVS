package ar.edu.utn.frba.dds.domain.datosColaboraciones;

import lombok.Setter;

@Setter
public class SensorTemperatura implements Sensor {
    private float temperaturaMinima;
    private float temperaturaMaxima;
    private Medicion ultimaMedicion;

    @Override
    public boolean estaEnCondiciones() {
        return this.ultimaMedicion.getTemperaturaActual() <= temperaturaMinima
                &&
                this.ultimaMedicion.getTemperaturaActual() <= temperaturaMaxima;
    }
}
