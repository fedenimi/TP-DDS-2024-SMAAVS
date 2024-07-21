package ar.edu.utn.frba.dds.modelo.entidades.colaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        return Period.between(heladera.getFechaYHoraInicio().toLocalDate(), LocalDate.now()).getMonths();
    }
}
