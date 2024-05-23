package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.domain.localizacion.Punto;
import ar.edu.utn.frba.dds.domain.personas.Colaborador;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TestRecomendador {
    Colaborador colaborador = new Colaborador();
    List<Punto> puntosSolicitados = colaborador.puntosDeHeladeraRecomendados(new Punto(50,50), 100);

    public TestRecomendador() throws IOException {
    }

    @Test
    public void primerPuntoOk () {
        Assert.assertEquals(80, puntosSolicitados.get(0).getLatitud(), 0);
        Assert.assertEquals(100, puntosSolicitados.get(0).getLongitud(), 0);
    }

    @Test
    public void cantidadDePuntosRecibidos() {
        Assert.assertEquals(2, puntosSolicitados.size());
    }
}
