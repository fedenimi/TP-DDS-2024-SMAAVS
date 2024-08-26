package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.frecuencia;

import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DonacionDeDinero;
import lombok.AllArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "frecuencia_anual")
public class Anual implements Frecuencia{
    @Id
    @GeneratedValue
    private Long id;
    public float puntajePara(DonacionDeDinero donacionDeDinero){
        return donacionDeDinero.anios();
    }
}
