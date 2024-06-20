package ar.edu.utn.frba.dds.domain.datosPersonas;

import ar.edu.utn.frba.dds.domain.datosColaboraciones.Heladera;
import lombok.Getter;

import java.time.LocalDateTime;

public class Uso {
    private Heladera heladera;
    @Getter private LocalDateTime fechaDeUso;
}
