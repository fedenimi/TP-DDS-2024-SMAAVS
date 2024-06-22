package ar.edu.utn.frba.dds.modelo.entidades.colaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;

public interface Puntuable {
    float puntaje();
    float getMultiplicador();

    Colaborador getColaborador();
    int cantidadDeMesesSiendoHeladera();
}
