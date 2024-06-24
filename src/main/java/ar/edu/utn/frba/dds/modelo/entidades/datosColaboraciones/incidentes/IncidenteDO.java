package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
@Builder
@Getter
public class IncidenteDO {
    private Estado estado;
    private Colaborador reportador;
    private String descripcion;
    private String foto;

    public static IncidenteDO of(Colaborador reportador, String descripcion, String foto) {
        return IncidenteDO
                .builder()
                .reportador(reportador)
                .descripcion(descripcion)
                .foto(foto)
                .estado(Estado.FALLA_TECNICA)
                .build();
    }

    public static IncidenteDO of(Estado estado) {
        return IncidenteDO
                .builder()
                .estado(estado)
                .build();
    }


}
