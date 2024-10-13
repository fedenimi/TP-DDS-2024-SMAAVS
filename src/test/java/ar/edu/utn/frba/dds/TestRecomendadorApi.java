package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.dtos.PuntoDonacionCreate;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.Punto;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.RecomendadorApi;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.RecomendadorDePuntos;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.utils.ServicioPuntos;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TestRecomendadorApi {
    @Test
    public void testRecomendadorApi() throws IOException {
        RecomendadorApi adapter = new RecomendadorApi();
        RecomendadorDePuntos recomendador = RecomendadorDePuntos.getInstance();
        recomendador.setAdapter(adapter);
        Assert.assertEquals(2, adapter.puntosDeHeladeraRecomendados(Punto.builder().latitud(-34.632d).longitud(-58.425d).build(), 1000).size());
        try {
            List<PuntoDonacionCreate> nuevoPunto = ServicioPuntos.instancia("http://127.0.0.1:8001/")
                    .agregarNuevoPunto("Parque Huron", -34.600d, -58.450d, "Av. Huron 123");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al agregar el punto: " + e.getMessage());
        }
        Assert.assertEquals(2, adapter.puntosDeHeladeraRecomendados(Punto.builder().latitud(-34.632d).longitud(-58.425d).build(), 1000).size());
    }
}
