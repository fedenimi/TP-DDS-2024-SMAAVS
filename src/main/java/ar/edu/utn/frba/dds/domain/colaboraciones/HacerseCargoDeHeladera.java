package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.domain.personas.Colaborador;
import lombok.Getter;

public class HacerseCargoDeHeladera implements Contribucion, Puntuable{
    private Heladera heladera;
    private Colaborador colaborador;

   @Getter private float multiplicador;

    @Override
    public void contribuir() {

    }
    @Override
    public void aumentarPuntaje() {

    }
    @Override
    public float puntaje() {
        return 0;
    }
}
