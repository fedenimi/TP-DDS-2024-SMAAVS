package ar.edu.utn.frba.dds.modelo.entidades.colaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.ColaboracionesProperties;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Registro;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@DiscriminatorValue("registro_personas_vulnerables")
@Builder
@AllArgsConstructor
@Setter
public class RegistroDePersonasVulnerables extends Puntuable {
    @Embedded
    private Registro registro;

    public RegistroDePersonasVulnerables(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    @Override
    public Double puntaje() {
        return 1D;
    }

    @Override
    public int cantidadDeMesesSiendoHeladera() {
        return 0;
    }

    @Override
    public Double getMultiplicador() {
        return Double.parseDouble(ColaboracionesProperties.getInstance().propertyFromName("registro_personas_vulnerables"));
    }
}
