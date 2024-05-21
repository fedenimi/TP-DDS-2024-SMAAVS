package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.datosColaboraciones.Frecuencia;
import ar.edu.utn.frba.dds.domain.personas.Colaborador;
import ar.edu.utn.frba.dds.domain.utils.Calculador;
import lombok.Getter;

import java.time.LocalDate;

public class DonacionDeDinero implements Contribucion, Puntuable{
    private LocalDate fechaDeInicio;
    private Integer monto;
    private Frecuencia frecuencia;
    @Getter private Colaborador colaborador;
    @Getter private float multiplicador;

    @Override
    public void contribuir() {
        Calculador.getInstance().aumentarPuntaje(this);
    }

    @Override
    public float puntaje() {
        return monto;
    }
}
