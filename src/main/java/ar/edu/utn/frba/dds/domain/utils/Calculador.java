package ar.edu.utn.frba.dds.domain.utils;

import ar.edu.utn.frba.dds.domain.colaboraciones.Puntuable;

public class Calculador {
    public float puntaje(Puntuable contribucion) {
        return contribucion.puntaje() * contribucion.getMultiplicador();
    }
}
