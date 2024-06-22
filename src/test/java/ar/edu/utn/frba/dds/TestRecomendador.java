package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.entidades.localizacion.AdapterRecomendadorApi;
import ar.edu.utn.frba.dds.entidades.localizacion.Punto;
import ar.edu.utn.frba.dds.entidades.localizacion.RecomendadorDePuntos;
import ar.edu.utn.frba.dds.entidades.personas.Colaborador;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestRecomendador {

    public TestRecomendador() throws IOException {
    }

    @Test
    public void dosPuntosEsperados () throws IOException {
        Colaborador colaborador = new Colaborador();

        AdapterRecomendadorApi adapter = mock(AdapterRecomendadorApi.class);
        RecomendadorDePuntos recomendador = RecomendadorDePuntos.getInstance();
        recomendador.setAdapter(adapter);
        Punto punto1 = new Punto(80, 100);
        Punto punto2 = new Punto(70, 90);
        List<Punto> puntos = Arrays.asList(punto1,punto2);
        when(adapter.puntosDeHeladeraRecomendados(ArgumentMatchers.any(Punto.class), ArgumentMatchers.anyInt()))
                .thenReturn(Arrays.asList(punto1, punto2));
        List<Punto> puntosSolicitados = colaborador.puntosDeHeladeraRecomendados(new Punto(50,50), 100);
        Assert.assertEquals(80, puntosSolicitados.get(0).getLatitud(), 0);
        Assert.assertEquals(100, puntosSolicitados.get(0).getLongitud(), 0);
        Assert.assertEquals(2, puntosSolicitados.size());
    }

    @Test
    public void cantidadDePuntosRecibidos() {
        //Assert.assertEquals(2, puntosSolicitados.size());
    }
}
