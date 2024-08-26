package ar.edu.utn.frba.dds.modelo.entidades.colaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.frecuencia.Frecuencia;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.utils.converters.FrecuenciaConverter;
import ar.edu.utn.frba.dds.modelo.entidades.utils.converters.LocalDateConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
@NoArgsConstructor
@Entity
@DiscriminatorValue("donacion_dinero")
public class DonacionDeDinero extends Puntuable {
    @Column(name = "fecha_de_inicio")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate fechaDeInicio;

    @Column(name = "monto", columnDefinition = "int")
    private Integer monto;

    @Convert(converter = FrecuenciaConverter.class)
    @Column(name = "frecuencia")
    private Frecuencia frecuencia;

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
