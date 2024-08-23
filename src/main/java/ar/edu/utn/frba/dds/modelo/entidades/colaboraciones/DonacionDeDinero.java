package ar.edu.utn.frba.dds.modelo.entidades.colaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.frecuencia.Frecuencia;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
@NoArgsConstructor
@Entity
@Table(name = "donacion_de_dinero")
public class DonacionDeDinero implements Puntuable{
    @Id
    @GeneratedValue
    private Long id;

    //TODO: converter
    private LocalDate fechaDeInicio;

    @Column(name = "monto", columnDefinition = "int")
    private Integer monto;

    @ManyToOne
    @JoinColumn(name = "frecuencia_id") //TODO: arreglar
    private Frecuencia frecuencia;

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    @Getter private Colaborador colaborador;

    @Column(name = "multiplicador", columnDefinition = "float")
    @Getter private float multiplicador;

    public DonacionDeDinero(Integer monto, Colaborador colaborador) {
        this.monto = monto;
        this.colaborador = colaborador;
    }

    @Override
    public float puntaje() {
        return this.monto * this.frecuencia.puntajePara(this);
    }

    @Override
    public int cantidadDeMesesSiendoHeladera() {
        return 0;
    }

    public float meses() {
        return Period.between(this.fechaDeInicio, LocalDate.now()).getMonths();
    }
    public float anios() {
        return Period.between(this.fechaDeInicio, LocalDate.now()).getYears();
    }
    
}
