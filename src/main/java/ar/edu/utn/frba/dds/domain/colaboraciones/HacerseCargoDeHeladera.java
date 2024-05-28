package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.domain.personas.Colaborador;
import lombok.Getter;

import java.time.LocalDate;
import java.time.Period;

public class HacerseCargoDeHeladera implements Puntuable{
    @Getter private Heladera heladera;
    @Getter private Colaborador colaborador;

   @Getter private float multiplicador;

    @Override
    public float puntaje() {

        return this.colaborador.sumatoriaDeMesesDeHeladerasActivas();
    }

    @Override
    public int cantidadDeMesesSiendoHeladera() {
        return Period.between(heladera.getFechaInicio(), LocalDate.now()).getMonths();
    }
}
