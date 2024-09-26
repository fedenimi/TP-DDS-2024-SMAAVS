package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.frecuencia;

import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DonacionDeDinero;
import lombok.AllArgsConstructor;

import javax.persistence.*;

public class Anual implements Frecuencia{
    public float puntajePara(DonacionDeDinero donacionDeDinero){
        return donacionDeDinero.anios();
    }
}
