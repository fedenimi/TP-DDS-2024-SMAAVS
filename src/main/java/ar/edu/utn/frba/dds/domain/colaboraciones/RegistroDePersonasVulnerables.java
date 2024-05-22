package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.datosColaboraciones.Registro;
import ar.edu.utn.frba.dds.domain.personas.Colaborador;
import ar.edu.utn.frba.dds.domain.utils.Calculador;
import lombok.Getter;

public class RegistroDePersonasVulnerables implements Contribucion, Puntuable {
    private Registro registro;
    @Getter private Colaborador colaborador;
    @Getter private float multiplicador;

    @Override
    public void contribuir() {
        Calculador.getInstance().aumentarPuntaje(this);
    }

    @Override
    public void Contribucion(Colaborador colaborador, Integer cantidad) {

    }

    @Override
    public float puntaje() {
        return 1;
    }
}
