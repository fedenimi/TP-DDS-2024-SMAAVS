package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.frecuencia;

import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DonacionDeDinero;

import javax.persistence.*;
public class Mensual implements Frecuencia{
    public float puntajePara(DonacionDeDinero donacionDeDinero){
        return donacionDeDinero.meses();
    }

}
