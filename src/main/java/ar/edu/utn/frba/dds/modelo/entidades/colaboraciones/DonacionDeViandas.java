package ar.edu.utn.frba.dds.modelo.entidades.colaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Vianda;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Getter
@Entity
@Table(name = "donacion_de_viandas")
public class DonacionDeViandas implements Puntuable{
    @OneToMany
    @JoinColumn(name = "vianda_id")
    private List<Vianda> viandasDonadas;

    //TODO: converter
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    private Colaborador colaborador;

    @Column(name = "multiplicador", columnDefinition = "float")
    private float multiplicador;


    public DonacionDeViandas(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public DonacionDeViandas(Colaborador colaborador, List<Vianda> viandas, LocalDate fecha) {
        this.colaborador = colaborador;
        this.viandasDonadas = viandas;
        this.fecha = fecha;
    }

    @Override
    public float puntaje() {
        return viandasDonadas.size();
    }

    @Override
    public int cantidadDeMesesSiendoHeladera() {
        return 0;
    }


    public Integer cantidadDeViandas() {
        return viandasDonadas.size();
    }
}
