package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.datosColaboraciones.Frecuencia;
import ar.edu.utn.frba.dds.domain.personas.Colaborador;
import lombok.Getter;

import java.time.LocalDate;

public class DonacionDeDinero implements Puntuable{
    private LocalDate fechaDeInicio;
    private Integer monto;
    private Frecuencia frecuencia;
    private Colaborador colaborador;
    @Getter private float multiplicador;
    @Override
    public void aumentarPuntaje() {
        colaborador.sumarPuntos(calculador.puntaje(this));
    }
    @Override
    public float puntaje() {
        return monto;
    }
}
