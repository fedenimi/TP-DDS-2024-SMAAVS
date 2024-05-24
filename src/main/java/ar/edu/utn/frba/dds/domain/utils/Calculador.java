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

    public float puntaje(List<Puntuable> contribuciones, float puntosCanjeados) {
        if(!contribuciones.isEmpty()){
            return this.mapToPuntaje(contribuciones).reduce(Float::sum).get() - puntosCanjeados;
        }
        return 0;
    }

    private Stream<Float> mapToPuntaje(List<Puntuable> contribuciones) {
        return contribuciones.stream().map(contribucion -> contribucion.puntaje()*contribucion.getMultiplicador());
    }
}
