package ar.edu.utn.frba.dds.entidades.datosColaboraciones;

import ar.edu.utn.frba.dds.entidades.localizacion.Punto;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Heladera {
    private Punto punto;
    private String direccion;
    private String nombre;
    private Integer capacidad;
    @Getter private LocalDate fechaInicio;
    private SensorTemperatura estadoTemperatura;
    private SensorMovimiento estadoMovimiento;

    public void cambiarTemperaturaMinima(float temperatura) {
        this.estadoTemperatura.setTemperaturaMinima(temperatura);
    }

    public void cambiarTemperaturaMaxima(float temperatura) {
        this.estadoTemperatura.setTemperaturaMaxima(temperatura);
    }

    public void cambiarTemperaturaActual(float temperatura) {
        this.estadoTemperatura.setUltimaMedicion(new Medicion(temperatura, LocalDateTime.now()));
    }

    public boolean estaActiva() {
     return this.estadoTemperatura.estaEnCondiciones() && this.estadoMovimiento.estaEnCondiciones();
    }
}
