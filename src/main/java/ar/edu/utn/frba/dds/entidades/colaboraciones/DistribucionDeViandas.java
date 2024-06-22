package ar.edu.utn.frba.dds.entidades.colaboraciones;

import ar.edu.utn.frba.dds.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.entidades.datosColaboraciones.Motivo;
import ar.edu.utn.frba.dds.entidades.personas.Colaborador;
import lombok.Getter;

import java.time.LocalDate;

public class DistribucionDeViandas implements Puntuable{
    private Heladera heladeraOrigen;
    private Heladera heladeraDestino;
    private Integer cantidadDeViandas;
    private Motivo motivoDistribucion;
    private LocalDate fecha;

    @Getter private Colaborador colaborador;
    @Getter private float multiplicador;

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
