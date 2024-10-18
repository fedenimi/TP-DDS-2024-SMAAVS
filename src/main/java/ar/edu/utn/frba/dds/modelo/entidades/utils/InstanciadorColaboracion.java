package ar.edu.utn.frba.dds.modelo.entidades.utils;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.*;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.frecuencia.Frecuencia;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.frecuencia.Unica;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioPuntuables;

import java.util.ArrayList;

public class InstanciadorColaboracion {

    public void agregarColaboracion(ArrayList<Puntuable> contribuciones, String formaColaboracion, Colaborador colaborador, int cantidad) {
        RepositorioPuntuables repositorioPuntuables = ServiceLocator.instanceOf(RepositorioPuntuables.class);
        switch(formaColaboracion) {
            case "DINERO":
                DonacionDeDinero donacionDeDinero = DonacionDeDinero.builder().monto(cantidad).frecuencia(new Unica()).build();
                donacionDeDinero.setColaborador(colaborador);
                contribuciones.add(donacionDeDinero);
                repositorioPuntuables.guardar(donacionDeDinero);
                colaborador.agregarPuntuable(donacionDeDinero);
                break;
            case "DONACION_VIANDAS":
                DonacionDeViandas donacionDeViandas = new DonacionDeViandas(colaborador);
                contribuciones.add(donacionDeViandas);
                repositorioPuntuables.guardar(donacionDeViandas);
                colaborador.agregarPuntuable(donacionDeViandas);
                break;
            case "REDISTRIBUCION_VIANDAS":
                DistribucionDeViandas distribucionDeViandas = new DistribucionDeViandas(cantidad, colaborador);
                contribuciones.add(distribucionDeViandas);
                repositorioPuntuables.guardar(distribucionDeViandas);
                colaborador.agregarPuntuable(distribucionDeViandas);
                break;
            case "ENTREGA_TARJETAS":
                for (int i = 0; i < cantidad; i++) {
                    RegistroDePersonasVulnerables registroDePersonasVulnerables = new RegistroDePersonasVulnerables(colaborador);
                    contribuciones.add(registroDePersonasVulnerables);
                    repositorioPuntuables.guardar(registroDePersonasVulnerables);
                    colaborador.agregarPuntuable(registroDePersonasVulnerables);
                }
                break;
        }
    }
}
