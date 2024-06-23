package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;

import java.util.List;

public class ReceptorSensorTemperatura extends Receptor{
    private List<Medicion> mediciones;
    private Heladera heladera;

    public boolean evaluarTemperatura(Temperatura temperatura) {
        return temperatura.getValor() >= heladera.obtenerTemperaturaMinima() && temperatura.getValor() <= heladera.obtenerTemperaturaMaxima();
    }
    public void agregarMedicion(Medicion medicion) {

    }



}
