package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.datosColaboraciones.Frecuencia;
import ar.edu.utn.frba.dds.domain.personas.Colaborador;

import java.time.LocalDate;

public class DonacionDeDinero {
    private LocalDate fechaDeInicio;
    private Integer monto;
    private Frecuencia frecuencia;
    private Colaborador colaborador;
    private float multiplicador;
}
