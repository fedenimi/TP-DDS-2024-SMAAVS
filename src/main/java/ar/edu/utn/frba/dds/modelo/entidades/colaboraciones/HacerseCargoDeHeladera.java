package ar.edu.utn.frba.dds.modelo.entidades.colaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@DiscriminatorValue("hacerse_cargo_de_heladera")
public class HacerseCargoDeHeladera extends Puntuable{
    @Transient
    @Getter private Heladera heladera;

    @Column(name = "multiplicador", columnDefinition = "float")
    @Getter private Double multiplicador;

    @Override
    public Double puntaje() {
        return this.colaborador.sumatoriaDeMesesDeHeladerasActivas().doubleValue();
    }

    @Override
    public int cantidadDeMesesSiendoHeladera() {
        return Period.between(heladera.getFechaYHoraInicio().toLocalDate(), LocalDate.now()).getMonths();
    }
}
