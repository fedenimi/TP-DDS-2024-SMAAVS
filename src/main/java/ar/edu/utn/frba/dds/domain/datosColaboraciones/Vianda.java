package ar.edu.utn.frba.dds.domain.datosColaboraciones;

import ar.edu.utn.frba.dds.domain.personas.Colaborador;

import java.time.LocalDate;

public class Vianda {
    private LocalDate fechaCaducidad;
    private String comida;
    private Colaborador colaborador;
    private Heladera heladera;
    private Integer calorias;
    private Integer peso;
    private boolean entregada;
}
