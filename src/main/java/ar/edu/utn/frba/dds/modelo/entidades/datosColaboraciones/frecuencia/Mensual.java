package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.frecuencia;

import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DonacionDeDinero;

public class Mensual {
    public float puntajePara(DonacionDeDinero donacionDeDinero){
        return donacionDeDinero.meses();
    }

}
