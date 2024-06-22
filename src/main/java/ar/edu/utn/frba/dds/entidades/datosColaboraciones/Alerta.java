package ar.edu.utn.frba.dds.entidades.datosColaboraciones;

import lombok.Getter;

import java.time.LocalDateTime;

public class Alerta {
    private LocalDateTime momentoAlerta;
   @Getter
   private boolean fueResuelta;
}
