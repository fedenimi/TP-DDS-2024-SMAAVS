package ar.edu.utn.frba.dds.domain.localizacion;

import java.util.List;

public interface AdapterRecomendadorApi {
     List<Punto> puntosDeHeladeraRecomendados(Punto punto, int radioEnMetros);
}
