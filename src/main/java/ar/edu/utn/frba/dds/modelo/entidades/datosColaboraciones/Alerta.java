package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones;

import lombok.Getter;

import java.time.LocalDateTime;

public class Alerta {
    private LocalDateTime momentoAlerta;
   @Getter
   private boolean fueResuelta;
}
