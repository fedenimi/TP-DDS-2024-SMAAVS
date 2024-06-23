package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera;

import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
public class Apertura {
    private Colaborador colaborador;
    private LocalDateTime fechaYHora;
}
