package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.frecuencia;

import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DonacionDeDinero;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "frecuencia_unica")
public class Unica {
    @Id
    @GeneratedValue
    private Long id;

    public float puntajePara(DonacionDeDinero donacionDeDinero){
        return 1;
    }
}
