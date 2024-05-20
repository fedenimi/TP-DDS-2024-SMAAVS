package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.domain.datosColaboraciones.Motivo;
import ar.edu.utn.frba.dds.domain.personas.Colaborador;

import java.time.LocalDate;

public class DistribucionDeViandas {
    private Heladera heladeraOrigen;
    private Heladera heladeraDestino;
    private Integer cantidadDeViandas;
    private Motivo motivoDistribucion;
    private LocalDate fecha;

    private Colaborador colaborador;
    private float multiplicador;
}
