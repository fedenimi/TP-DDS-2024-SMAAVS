package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.domain.datosColaboraciones.Motivo;
import ar.edu.utn.frba.dds.domain.personas.Colaborador;
import lombok.Getter;

import java.time.LocalDate;

public class DistribucionDeViandas implements Puntuable{
    private Heladera heladeraOrigen;
    private Heladera heladeraDestino;
    private Integer cantidadDeViandas;
    private Motivo motivoDistribucion;
    private LocalDate fecha;

    private Colaborador colaborador;
 @Getter private float multiplicador;

    @Override
    public void aumentarPuntaje() {

    }
    @Override
    public float puntaje() {
        return 0;
    }
}
