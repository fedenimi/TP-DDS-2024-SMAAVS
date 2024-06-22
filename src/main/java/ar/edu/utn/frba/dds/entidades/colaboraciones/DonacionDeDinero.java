package ar.edu.utn.frba.dds.entidades.colaboraciones;

import ar.edu.utn.frba.dds.entidades.datosColaboraciones.frecuencia.Frecuencia;
import ar.edu.utn.frba.dds.entidades.personas.Colaborador;
import lombok.Getter;

import java.time.LocalDate;
import java.time.Period;

public class DonacionDeDinero implements Puntuable{
    private LocalDate fechaDeInicio;
    private Integer monto;
    private Frecuencia frecuencia;
    @Getter private Colaborador colaborador;
    @Getter private float multiplicador;

    public DonacionDeDinero(Integer monto, Colaborador colaborador) {
        this.monto = monto;
        this.colaborador = colaborador;
    }

    @Override
    public float puntaje() {
        return this.monto * this.frecuencia.puntajePara(this);
    }

    @Override
    public int cantidadDeMesesSiendoHeladera() {
        return 0;
    }

    public float meses() {
        return Period.between(this.fechaDeInicio, LocalDate.now()).getMonths();
    }
    public float anios() {
        return Period.between(this.fechaDeInicio, LocalDate.now()).getYears();
    }
    
}
