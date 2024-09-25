package ar.edu.utn.frba.dds.servicios;

import ar.edu.utn.frba.dds.dtos.DistribuirViandasDTO;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DistribucionDeViandas;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.Puntuable;

public class ServiceDistribuirViandas {
    public DistribuirViandasDTO toDistribuirViandasDTO(DistribucionDeViandas distribucionDeViandas) {
        return DistribuirViandasDTO.builder().
                id(distribucionDeViandas.getId().toString()).
                heladera_origen(distribucionDeViandas.getHeladeraOrigen().getId().toString()).
                heladera_destino(distribucionDeViandas.getHeladeraDestino().getId().toString()).
                motivo(distribucionDeViandas.getMotivoDistribucion().toString()).
                cantViandas(distribucionDeViandas.getCantidadDeViandas().toString()).
                build();

    }
}
