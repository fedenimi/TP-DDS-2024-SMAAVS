package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.datosColaboraciones.Registro;
import ar.edu.utn.frba.dds.domain.personas.Colaborador;
import lombok.Getter;

public class RegistroDePersonasVulnerables implements Contribucion, Puntuable {
    private Registro registro;
    @Getter private Colaborador colaborador;
    @Getter private float multiplicador;

    public RegistroDePersonasVulnerables(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    @Override
    public void contribuir() {

    }

    @Override
    public float puntaje() {
        return 1;
    }

    @Override
    public int cantidadDeMesesSiendoHeladera() {
        return 0;
    }
}
