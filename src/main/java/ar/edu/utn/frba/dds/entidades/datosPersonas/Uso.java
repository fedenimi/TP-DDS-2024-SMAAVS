package ar.edu.utn.frba.dds.entidades.datosPersonas;

import ar.edu.utn.frba.dds.entidades.datosColaboraciones.Heladera;
import lombok.Getter;

import java.time.LocalDateTime;

public class Uso {
    private Heladera heladera;
    @Getter private LocalDateTime fechaDeUso;
}
