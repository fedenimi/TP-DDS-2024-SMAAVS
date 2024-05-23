package ar.edu.utn.frba.dds.domain.datosColaboraciones.frecuencia;

import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionDeDinero;

public class Mensual {
    public float puntajePara(DonacionDeDinero donacionDeDinero){
        return donacionDeDinero.meses();
    }

}
