package ar.edu.utn.frba.dds.entidades.colaboraciones;

import ar.edu.utn.frba.dds.entidades.personas.Colaborador;

public interface Puntuable {
    float puntaje();
    float getMultiplicador();

    Colaborador getColaborador();
    int cantidadDeMesesSiendoHeladera();
}
