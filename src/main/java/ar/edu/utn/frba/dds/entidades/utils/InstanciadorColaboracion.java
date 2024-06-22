package ar.edu.utn.frba.dds.entidades.utils;

import ar.edu.utn.frba.dds.entidades.colaboraciones.*;
import ar.edu.utn.frba.dds.entidades.personas.Colaborador;

import java.util.ArrayList;

public class InstanciadorColaboracion {
    public void agregarColaboracion(ArrayList<Puntuable> contribuciones, String formaColaboracion, Colaborador colaborador, int cantidad) {
        switch(formaColaboracion) {
            case "DINERO":
                contribuciones.add(new DonacionDeDinero(cantidad, colaborador));
                break;
            case "DONACION_VIANDAS":
                contribuciones.add(new DonacionDeViandas(colaborador));
                break;
            case "REDISTRIBUCION_VIANDAS":
                contribuciones.add(new DistribucionDeViandas(cantidad, colaborador));
                break;
            case "ENTREGA_TARJETAS":
                for (int i = 0; i < cantidad; i++) {
                    contribuciones.add(new RegistroDePersonasVulnerables(colaborador));
                }
                break;
        }
    }
}
