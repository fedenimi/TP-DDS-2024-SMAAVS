package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
public class FallaTecnica {
    private Colaborador reportador;
    private  String descripcion;
    private String foto;
    private LocalDateTime fechaYHora;
    private Heladera heladera;
}
