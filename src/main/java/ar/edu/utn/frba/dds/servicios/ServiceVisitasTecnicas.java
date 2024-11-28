package ar.edu.utn.frba.dds.servicios;

import ar.edu.utn.frba.dds.dtos.VisitaTecnicaDTO;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.VisitaTecnica;

public class ServiceVisitasTecnicas {
    public static VisitaTecnicaDTO toVisitaTecnicaDTO(VisitaTecnica visitaTecnica) {
        return VisitaTecnicaDTO.builder().
                id(visitaTecnica.getId().toString()).
                descripcion(visitaTecnica.getDescripcion()).
                foto(visitaTecnica.getFoto()).
                fechaVisita(visitaTecnica.getFechaVisita().toString()).
                build();
    }
}
