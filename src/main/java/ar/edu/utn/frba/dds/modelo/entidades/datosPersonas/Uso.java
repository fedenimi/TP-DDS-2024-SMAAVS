package ar.edu.utn.frba.dds.modelo.entidades.datosPersonas;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import lombok.Getter;

import java.time.LocalDateTime;

public class Uso {
    private Heladera heladera;
    @Getter private LocalDateTime fechaDeUso;
}
