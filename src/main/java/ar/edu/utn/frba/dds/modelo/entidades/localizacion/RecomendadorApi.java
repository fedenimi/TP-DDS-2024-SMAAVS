package ar.edu.utn.frba.dds.modelo.entidades.localizacion;

import ar.edu.utn.frba.dds.modelo.entidades.utils.ServicioPuntos;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;
@NoArgsConstructor
public class RecomendadorApi implements AdapterRecomendadorApi{
    @Override
    //http://127.0.0.1:8123/recomendaciones/?lat=90&long=90&limit=5


    public List<Punto> puntosDeHeladeraRecomendados(Punto punto, int radioEnMetros) throws IOException {
        return ServicioPuntos.
                instancia("http://127.0.0.1:8123/recomendaciones/?lat=" + punto.getLatitud() + "&long=" + punto.getLongitud() + "&limit=3").
                listadoDePuntos().getPuntos();
    }
}
