package ar.edu.utn.frba.dds.modelo.entidades.colaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Vianda;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.utils.converters.LocalDateConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("donacion_viandas")
public class DonacionDeViandas extends Puntuable{
    @OneToMany
    @JoinColumn(name = "vianda_id")
    private List<Vianda> viandasDonadas;

    @Column(name = "fecha")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate fecha;

    @Column(name = "multiplicador", columnDefinition = "float")
    private Double multiplicador;


    public DonacionDeViandas(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public DonacionDeViandas(Colaborador colaborador, List<Vianda> viandas, LocalDate fecha) {
        this.colaborador = colaborador;
        this.viandasDonadas = viandas;
        this.fecha = fecha;
    }

    @Override
    public Double puntaje() {
        Integer ret = viandasDonadas.size();
        return ret.doubleValue();
    }

    @Override
    public int cantidadDeMesesSiendoHeladera() {
        return 0;
    }


    public Integer cantidadDeViandas() {
        return viandasDonadas.size();
    }
}
