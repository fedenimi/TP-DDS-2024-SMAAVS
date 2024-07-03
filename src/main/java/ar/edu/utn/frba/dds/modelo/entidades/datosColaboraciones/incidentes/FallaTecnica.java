package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
@AllArgsConstructor
public class FallaTecnica {
    private Colaborador reportador;
    private String descripcion;
    private String foto;
    @Getter private LocalDateTime fechaYHora;
    @Getter private Heladera heladera;
}
