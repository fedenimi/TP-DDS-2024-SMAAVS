package ar.edu.utn.frba.dds.domain.datosColaboraciones;

import ar.edu.utn.frba.dds.domain.localizacion.Punto;

import java.time.LocalDate;

public class Heladera {
    private Punto punto;
    private String direccion;
    private String nombre;
    private Integer capacidad;
    private LocalDate fechaInicio;
    private SensorTemperatura estadoTemperatura;
    private SensorMovimiento estadoMovimiento;
}
