package ar.edu.utn.frba.dds.entidades.datosColaboraciones.frecuencia;

import ar.edu.utn.frba.dds.entidades.colaboraciones.DonacionDeDinero;

public class Anual {
    public float puntajePara(DonacionDeDinero donacionDeDinero){
        return donacionDeDinero.anios();
    }
}
