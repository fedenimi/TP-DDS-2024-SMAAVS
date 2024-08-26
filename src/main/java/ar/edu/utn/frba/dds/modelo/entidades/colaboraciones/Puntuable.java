package ar.edu.utn.frba.dds.modelo.entidades.colaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "puntuable")
@DiscriminatorColumn(name = "tipo_puntuable")
public abstract class Puntuable {
    @Id
    @GeneratedValue
    private Long id;
    public abstract float puntaje();

    public abstract float getMultiplicador();

    public abstract Colaborador getColaborador();
    public int cantidadDeMesesSiendoHeladera(){
        return 0;
    }
}
