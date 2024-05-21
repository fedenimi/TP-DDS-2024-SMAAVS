package ar.edu.utn.frba.dds.domain.datosColaboraciones;

import lombok.Setter;

public class SensorTemperatura implements Sensor {
    @Setter private float temperaturaMinima;
    @Setter private float temperaturaMaxima;
    @Setter private Medicion ultimaMedicion;

    @Override
    public boolean estaEnCondiciones() {
        return this.ultimaMedicion.getTemperaturaActual() <= temperaturaMinima
                &&
                this.ultimaMedicion.getTemperaturaActual() <= temperaturaMaxima;
    }
}
