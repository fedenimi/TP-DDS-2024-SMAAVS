package ar.edu.utn.frba.dds.modelo.entidades.colaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "puntuable")
@DiscriminatorColumn(name = "tipo_puntuable")
public abstract class Puntuable {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    @Getter
    protected Colaborador colaborador;
    public abstract float puntaje();

    public abstract float getMultiplicador();
    public int cantidadDeMesesSiendoHeladera(){
        return 0;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }
}
