package ar.edu.utn.frba.dds.modelo.entidades.localizacion;

import java.io.IOException;
import java.util.List;

public interface AdapterRecomendadorApi {
     List<Punto> puntosDeHeladeraRecomendados(Punto punto, int radioEnMetros) throws IOException;
}
