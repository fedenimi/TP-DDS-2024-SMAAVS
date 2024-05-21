package ar.edu.utn.frba.dds.domain.datosColaboraciones;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
public class Medicion {
    @Getter private float temperaturaActual;
    private LocalDateTime tiempoActual;
}
