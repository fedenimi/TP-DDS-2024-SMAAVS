package ar.edu.utn.frba.dds.servicios;

import ar.edu.utn.frba.dds.dtos.AlertaDTO;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;

public class ServiceAlertas {
    public AlertaDTO toAlertaDTO(Alerta alerta) {
        return AlertaDTO.builder().
                id(alerta.getId().toString()).
                tipo(alerta.getTipoAlerta().toString()).
                id_heladera(alerta.getHeladera().getId().toString()).
                build();

    }
}
