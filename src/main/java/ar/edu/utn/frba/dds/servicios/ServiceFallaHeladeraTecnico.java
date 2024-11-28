package ar.edu.utn.frba.dds.servicios;

import ar.edu.utn.frba.dds.dtos.FalloHeladeraTecnicoDTO;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.FalloHeladeraTecnico;

public class ServiceFallaHeladeraTecnico {
    public static FalloHeladeraTecnicoDTO toFalloHeladeraTecnicoDTO(FalloHeladeraTecnico falloHeladeraTecnico) {
        return FalloHeladeraTecnicoDTO.
                builder().
                id(falloHeladeraTecnico.getId().toString()).
                fechaYhora(falloHeladeraTecnico.getFechaYHora().toString()).
                heladera(ServiceHeladeras.toHeladeraDTO(falloHeladeraTecnico.getHeladera())).
                build();
    }
}
