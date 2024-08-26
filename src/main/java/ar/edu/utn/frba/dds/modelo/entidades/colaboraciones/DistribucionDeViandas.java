package ar.edu.utn.frba.dds.modelo.entidades.colaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Motivo;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
@Getter
@NoArgsConstructor
@Entity
@DiscriminatorValue("distribucion_viandas")
public class DistribucionDeViandas extends Puntuable {
    @ManyToOne
    @JoinColumn(name = "heladera_origen_id")
    private Heladera heladeraOrigen;

    @ManyToOne
    @JoinColumn(name = "heladera_destino_id")
    private Heladera heladeraDestino;

    @Column(name = "cantidad_de_viandas", columnDefinition = "int")
    private Integer cantidadDeViandas;

    @Column(name = "motivo_distribucion")
    @Enumerated(EnumType.STRING)
    private Motivo motivoDistribucion;

    //TODO: converter
    private LocalDate fecha;

    @Column(name = "multiplicador", columnDefinition = "float")
    @Getter private float multiplicador;

    public DistribucionDeViandas(Integer cantidadDeViandas, Colaborador colaborador, Heladera heladeraDestino, Heladera heladeraOrigen, LocalDate fecha) {
        this.cantidadDeViandas = cantidadDeViandas;
        this.heladeraDestino = heladeraDestino;
        this.fecha = fecha;
        this.heladeraOrigen = heladeraOrigen;
    }

    public DistribucionDeViandas(Integer cantidadDeViandas, Colaborador colaborador) {
        this.cantidadDeViandas = cantidadDeViandas;
        this.colaborador = colaborador;
    }

    @Override
    public float puntaje() {
        return cantidadDeViandas;
    }

    @Override
    public int cantidadDeMesesSiendoHeladera() {
        return 0;
    }
}
