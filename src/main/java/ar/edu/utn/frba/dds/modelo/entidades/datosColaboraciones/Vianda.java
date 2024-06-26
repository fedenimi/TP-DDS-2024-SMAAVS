package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import lombok.Getter;

import java.time.LocalDate;

public class Vianda {
    private LocalDate fechaCaducidad;
    private String comida;
    @Getter private Colaborador colaborador;
    @Getter private Heladera heladera;
    private Integer calorias;
    private Integer peso;
    private boolean entregada;

    public Vianda(Heladera heladera) {
        this.heladera = heladera;
    }
}
