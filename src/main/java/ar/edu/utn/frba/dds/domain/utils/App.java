package ar.edu.utn.frba.dds.domain.utils;


import ar.edu.utn.frba.dds.domain.colaboraciones.Contribucion;
import ar.edu.utn.frba.dds.domain.colaboraciones.Puntuable;
import ar.edu.utn.frba.dds.domain.datosPersonas.MedioDeContacto;
import ar.edu.utn.frba.dds.domain.datosPersonas.TipoDeColaborador;
import ar.edu.utn.frba.dds.domain.datosPersonas.TipoDeContacto;
import ar.edu.utn.frba.dds.domain.datosPersonas.TipoDocumento;
import ar.edu.utn.frba.dds.domain.personas.Colaborador;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class App
{
    //TODO: ESTA CLASE ES PARA PROBAR EL LECTOR, DESPUES HAY QUE BORARLA
    public static void main( String[] args ) throws Exception
    {
        LectorCSV lectorCSV = new LectorCSV();

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




    }
}
