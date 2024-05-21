package ar.edu.utn.frba.dds.domain.utils;

import ar.edu.utn.frba.dds.domain.colaboraciones.Puntuable;

public class Calculador {

    private static Calculador instance = null;
    public static Calculador getInstance() {
        if(instance == null)
            instance = new Calculador();
        return instance;
    }

    public void aumentarPuntaje(Puntuable contribucion) {
        contribucion.getColaborador().sumarPuntos(contribucion.puntaje() * contribucion.getMultiplicador());
    }
}
