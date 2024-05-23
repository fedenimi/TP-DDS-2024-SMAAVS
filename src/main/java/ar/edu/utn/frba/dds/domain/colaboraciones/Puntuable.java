package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.personas.Colaborador;

public interface Puntuable {
    float puntaje();
    float getMultiplicador();

    Colaborador getColaborador();
    int cantidadDeMesesActiva();
}
