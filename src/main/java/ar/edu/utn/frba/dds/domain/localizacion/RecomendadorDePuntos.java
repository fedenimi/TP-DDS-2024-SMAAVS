package ar.edu.utn.frba.dds.domain.localizacion;

import java.util.List;

public class RecomendadorDePuntos {
    private AdapterRecomendadorApi adapter;

    public List<Punto> puntosDeHeladeraRecomendados(Punto punto, int radioEnMetros) {

        return adapter.puntosDeHeladeraRecomendados(punto, radioEnMetros);
    }
}
