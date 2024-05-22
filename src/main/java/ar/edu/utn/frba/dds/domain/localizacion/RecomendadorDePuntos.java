package ar.edu.utn.frba.dds.domain.localizacion;

import ar.edu.utn.frba.dds.domain.utils.Calculador;

import java.io.IOException;
import java.util.List;

public class RecomendadorDePuntos {
    private AdapterRecomendadorApi adapter = new RecomendadorApi();

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
