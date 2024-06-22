package ar.edu.utn.frba.dds.entidades.localizacion;

import lombok.Setter;

import java.io.IOException;
import java.util.List;


public class RecomendadorDePuntos {
    @Setter private AdapterRecomendadorApi adapter;


    private static RecomendadorDePuntos instance = null;
    public static RecomendadorDePuntos getInstance() {
        if(instance == null)
            instance = new RecomendadorDePuntos();
        return instance;
    }

    public List<Punto> puntosDeHeladeraRecomendados(Punto punto, int radioEnMetros) throws IOException {

        return adapter.puntosDeHeladeraRecomendados(punto, radioEnMetros);
    }
}
