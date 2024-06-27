package ar.edu.utn.frba.dds.modelo.entidades.colaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Motivo;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import lombok.Getter;

import java.time.LocalDate;
@Getter
public class DistribucionDeViandas implements Puntuable{
    private Heladera heladeraOrigen;
    private Heladera heladeraDestino;
    private Integer cantidadDeViandas;
    private Motivo motivoDistribucion;
    private LocalDate fecha;

    @Getter private Colaborador colaborador;
    @Getter private float multiplicador;

    public DistribucionDeViandas(Integer cantidadDeViandas, Colaborador colaborador, Heladera heladeraDestino, Heladera heladeraOrigen, LocalDate fecha) {
        this.cantidadDeViandas = cantidadDeViandas;
        this.colaborador = colaborador;
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
