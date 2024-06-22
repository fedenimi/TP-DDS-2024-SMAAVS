package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.entidades.colaboraciones.Puntuable;
import ar.edu.utn.frba.dds.entidades.datosPersonas.MedioDeContacto;
import ar.edu.utn.frba.dds.entidades.datosPersonas.TipoDeColaborador;
import ar.edu.utn.frba.dds.entidades.datosPersonas.TipoDeContacto;
import ar.edu.utn.frba.dds.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.entidades.utils.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class TestLectorCSV {

    @Test
    public void cincoColaboradoresLeidos() throws Exception {
        IEnviadorDeMail enviadorDeMail = mock(IEnviadorDeMail.class);
        InstanciadorColaborador instanciadorColaborador = new InstanciadorColaborador(enviadorDeMail);
        LectorCSV lectorCSV = new LectorCSV(instanciadorColaborador);

        List<Colaborador> colaboradores = new ArrayList<Colaborador>();
        List<MedioDeContacto> mediosDeContacto = new ArrayList<>();
        mediosDeContacto.add(new MedioDeContacto("mail@mail.com", TipoDeContacto.MAIL));
        Colaborador colaborador1 = new Colaborador(TipoDeColaborador.HUMANA, mediosDeContacto, "DNI", "1234", "Jorge", "Jor");
        colaboradores.add(colaborador1);
        List<MedioDeContacto> mediosDeContacto2 = new ArrayList<>();
        mediosDeContacto.add(new MedioDeContacto("mail@mail.com", TipoDeContacto.MAIL));
        Colaborador colaborador2 = new Colaborador(TipoDeColaborador.HUMANA, mediosDeContacto, "DNI", "12345", "Manuel", "Man");
        colaboradores.add(colaborador2);

        List<Puntuable> contribuciones = lectorCSV.cargarContribuciones(colaboradores ,new File("src/colaboraciones.csv"));
        Assert.assertEquals(contribuciones.size(), 16);
        Assert.assertEquals(colaboradores.size(), 7);
    }
}
