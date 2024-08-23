package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.frecuencia;

import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DonacionDeDinero;

import javax.persistence.*;
@Entity
@Table(name = "frecuencia_mensual")
public class Mensual {
    @Id
    @GeneratedValue
    private Long id;
    public float puntajePara(DonacionDeDinero donacionDeDinero){
        return donacionDeDinero.meses();
    }

}
