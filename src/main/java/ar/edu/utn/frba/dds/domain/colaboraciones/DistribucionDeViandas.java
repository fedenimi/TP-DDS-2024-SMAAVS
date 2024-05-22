package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.domain.datosColaboraciones.Motivo;
import ar.edu.utn.frba.dds.domain.personas.Colaborador;
import ar.edu.utn.frba.dds.domain.utils.Calculador;
import lombok.Getter;

import java.time.LocalDate;

public class DistribucionDeViandas implements Contribucion,Puntuable{
    private Heladera heladeraOrigen;
    private Heladera heladeraDestino;
    private Integer cantidadDeViandas;
    private Motivo motivoDistribucion;
    private LocalDate fecha;

    @Getter private Colaborador colaborador;
    @Getter private float multiplicador;

    @Override
    public void Contribucion(Colaborador colaborador, Integer cantidad){
        this.colaborador = colaborador;
        this.cantidadDeViandas = cantidad;
    }
    @Override
    public void contribuir() {
        Calculador.getInstance().aumentarPuntaje(this);
    }

    @Override
    public float puntaje() {
        return cantidadDeViandas;
    }
}
