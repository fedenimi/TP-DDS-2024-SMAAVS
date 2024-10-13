package ar.edu.utn.frba.dds.modelo.entidades.colaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.ColaboracionesProperties;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@DiscriminatorValue("hacerse_cargo_de_heladera")
@Setter
public class HacerseCargoDeHeladera extends Puntuable{

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "heladera_id", referencedColumnName = "id")
    @Getter private Heladera heladera;

    @Override
    public Double puntaje() {
        return this.colaborador.sumatoriaDeMesesDeHeladerasActivas().doubleValue();
    }

    @Override
    public int cantidadDeMesesSiendoHeladera() {
        return Period.between(heladera.getFechaYHoraInicio().toLocalDate(), LocalDate.now()).getMonths();
    }

    @Override
    public Double getMultiplicador() {
        return Double.parseDouble(ColaboracionesProperties.getInstance().propertyFromName("hacerse_cargo_de_heladera"));
    }
}
