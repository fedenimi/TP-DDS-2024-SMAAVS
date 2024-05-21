package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.domain.personas.Colaborador;
import ar.edu.utn.frba.dds.domain.utils.Calculador;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class HacerseCargoDeHeladera implements Contribucion, Puntuable{
    @Getter private Heladera heladera;
    @Getter private Colaborador colaborador;

   @Getter private float multiplicador;

    @Override
    public void contribuir() {
        Calculador.getInstance().aumentarPuntaje(this);
    }
    @Override
    public float puntaje() {
        return cantidadDeMesesActivas();
    }

    private int cantidadDeMesesActivas() {
        return Period.between(heladera.getFechaInicio(), LocalDate.now()).getMonths();
    }
}
