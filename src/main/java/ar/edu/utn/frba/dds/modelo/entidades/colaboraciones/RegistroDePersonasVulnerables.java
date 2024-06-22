package ar.edu.utn.frba.dds.modelo.entidades.colaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Registro;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import lombok.Getter;

public class RegistroDePersonasVulnerables implements Puntuable {
    private Registro registro;
    @Getter private Colaborador colaborador;
    @Getter private float multiplicador;

    public RegistroDePersonasVulnerables(Colaborador colaborador) {
        this.colaborador = colaborador;
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
