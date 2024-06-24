package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores;

import java.time.LocalDateTime;
import java.util.List;

public class ReceptorSensorTemperatura extends Receptor{
    private List<Medicion> mediciones;

    public boolean evaluarTemperatura(Temperatura temperatura) {
        return temperatura.getValor() >= this.getHeladera().obtenerTemperaturaMinima() && temperatura.getValor() <= this.getHeladera().obtenerTemperaturaMaxima();
    }
    public void agregarMedicion(String temperatura) {
        Medicion medicion = new Medicion(Float.parseFloat(temperatura), LocalDateTime.now());
        mediciones.add(medicion);
    }



}
