package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera;

import ar.edu.utn.frba.dds.modelo.entidades.personas.Tecnico;

import java.time.LocalDate;

public class VisitaTecnica {
    private Boolean fueSolucionado;
    private String descripcion;
    private String foto;
    private LocalDate fechaVisita;
    private Tecnico tecnico;
}
