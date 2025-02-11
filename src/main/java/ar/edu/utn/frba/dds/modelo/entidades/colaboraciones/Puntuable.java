package ar.edu.utn.frba.dds.modelo.entidades.colaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "puntuable")
@DiscriminatorColumn(name = "tipo_puntuable")
public abstract class Puntuable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    @Getter
    @Setter
    protected Colaborador colaborador;
    public abstract Double puntaje();

    public abstract Double getMultiplicador();
    public int cantidadDeMesesSiendoHeladera(){
        return 0;
    }

}
