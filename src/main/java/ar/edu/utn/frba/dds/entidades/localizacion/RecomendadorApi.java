package ar.edu.utn.frba.dds.entidades.localizacion;

import ar.edu.utn.frba.dds.entidades.utils.ServicioPuntos;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;
@NoArgsConstructor
public class RecomendadorApi implements AdapterRecomendadorApi{
    @Override
    public List<Punto> puntosDeHeladeraRecomendados(Punto punto, int radioEnMetros) throws IOException {
        return ServicioPuntos.instancia().listadoDePuntos().getPuntos();
    }
}
