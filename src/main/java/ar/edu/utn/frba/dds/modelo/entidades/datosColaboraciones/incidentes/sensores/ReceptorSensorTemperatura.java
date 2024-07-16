package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.CreadorAlerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
public class ReceptorSensorTemperatura {
    private List<Medicion> mediciones;
    private Heladera heladera;
    public boolean evaluarTemperatura(Temperatura temperatura) {
        return temperatura.getValor() >= heladera.obtenerTemperaturaMinima() && temperatura.getValor() <= this.getHeladera().obtenerTemperaturaMaxima();
    }
    public void agregarMedicion(String temperatura) {
        Medicion medicion = new Medicion(Float.parseFloat(temperatura), LocalDateTime.now());
        mediciones.add(medicion);
    }

    public Alerta crearAlerta(Heladera heladera, Estado estado) {
        return CreadorAlerta.getInstance().crearAlerta(heladera, estado);
    }

}
