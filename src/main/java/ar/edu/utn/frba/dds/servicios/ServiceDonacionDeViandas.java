package ar.edu.utn.frba.dds.servicios;

import ar.edu.utn.frba.dds.dtos.DonacionDeDineroDTO;
import ar.edu.utn.frba.dds.dtos.DonacionDeViandasDTO;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DonacionDeViandas;

public class ServiceDonacionDeViandas {
    public static DonacionDeViandasDTO toDonacionDeViandasDTO(DonacionDeViandas donacionDeViandas) {
        return DonacionDeViandasDTO.builder().
                id(donacionDeViandas.getId().toString()).
        build();
    }
}
