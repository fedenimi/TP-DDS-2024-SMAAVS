package ar.edu.utn.frba.dds.domain.utils;

import ar.edu.utn.frba.dds.domain.colaboraciones.Puntuable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Calculador {

    private static Calculador instance = null;
    public static Calculador getInstance() {
        if(instance == null)
            instance = new Calculador();
        return instance;
    }

    public Optional<Float> puntaje(List<Puntuable> contribuciones) {
        return this.mapToPuntaje(contribuciones).reduce(Float::sum);
    }

    private Stream<Float> mapToPuntaje(List<Puntuable> contribuciones) {
        return contribuciones.stream().map(contribucion -> contribucion.puntaje()*contribucion.getMultiplicador());
    }
}
