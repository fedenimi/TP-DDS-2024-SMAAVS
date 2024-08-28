package ar.edu.utn.frba.dds.modelo.entidades.colaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Registro;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "registro_de_personas_vulnerables")
public class RegistroDePersonasVulnerables extends Puntuable {
    @Transient //TODO: transient
    private Registro registro;

    @Column(name = "multiplicador", columnDefinition = "float")
    @Getter private float multiplicador;

    public RegistroDePersonasVulnerables(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    @Override
    public float puntaje() {
        return 1;
    }

    @Override
    public int cantidadDeMesesSiendoHeladera() {
        return 0;
    }
}
