package ar.edu.utn.frba.dds.domain.datosColaboraciones.frecuencia;

import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionDeDinero;

import java.time.Period;

public class Anual {
    public float puntajePara(DonacionDeDinero donacionDeDinero){
        return donacionDeDinero.anios();
    }
}
