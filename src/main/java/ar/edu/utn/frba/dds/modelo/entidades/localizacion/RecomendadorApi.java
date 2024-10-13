package ar.edu.utn.frba.dds.modelo.entidades.localizacion;

import ar.edu.utn.frba.dds.modelo.entidades.utils.ServicioPuntos;
import lombok.NoArgsConstructor;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.io.IOException;
import java.util.List;
@NoArgsConstructor
public class RecomendadorApi implements AdapterRecomendadorApi{
    @Override

    @GET
    public List<Punto> puntosDeHeladeraRecomendados(Punto punto, int radioEnMetros) throws IOException {
        String baseUrl = "http://127.0.0.1:8001/";
                ///recomendaciones/?lat=" + punto.getLatitud() + "&long=" + punto.getLongitud() + "&limit=2";
        return ServicioPuntos.instancia(baseUrl).listadoDePuntos(punto.getLatitud(), punto.getLongitud(), 20);

        //http://127.0.0.1:8001/recomendaciones/?lat=90&long=90&limit=5
       // return ServicioPuntos.
       //         instancia("http://127.0.0.1:8001/recomendaciones/?lat=" + punto.getLatitud() + "&long=" + punto.getLongitud() + "&limit=3").
       //         listadoDePuntos().getPuntos();
    }
    @POST
    public void agregarPunto(Punto punto) {
        // TODO Auto-generated method stub

    }
}
