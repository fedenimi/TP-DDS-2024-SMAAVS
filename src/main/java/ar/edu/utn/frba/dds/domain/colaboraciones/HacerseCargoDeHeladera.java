package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.domain.personas.Colaborador;
import lombok.Getter;

import java.time.LocalDate;
import java.time.Period;

public class HacerseCargoDeHeladera implements Contribucion, Puntuable{
    @Getter private Heladera heladera;
    @Getter private Colaborador colaborador;

   @Getter private float multiplicador;

    @Override
    public void contribuir() {

    }


    @Override
    public float puntaje() {

        return this.colaborador.sumatoriaDeMesesDeHeladerasActivas();
    }

    @Override
    public int cantidadDeMesesActiva() {
        return Period.between(heladera.getFechaInicio(), LocalDate.now()).getMonths();
    }
}
