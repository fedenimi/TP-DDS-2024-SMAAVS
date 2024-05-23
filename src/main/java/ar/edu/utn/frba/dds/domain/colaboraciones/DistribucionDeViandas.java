package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.domain.datosColaboraciones.Motivo;
import ar.edu.utn.frba.dds.domain.personas.Colaborador;
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

    public DistribucionDeViandas(Integer cantidadDeViandas, Colaborador colaborador) {
        this.cantidadDeViandas = cantidadDeViandas;
        this.colaborador = colaborador;
    }

    @Override
    public void contribuir() {

    }

    @Override
    public float puntaje() {
        return cantidadDeViandas;
    }

    @Override
    public int cantidadDeMesesActiva() {
        return 0;
    }
}
