package ar.edu.utn.frba.dds.servicios;

import ar.edu.utn.frba.dds.dtos.DonacionDeDineroDTO;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DonacionDeDinero;

public class ServiceDonacionDeDinero {
    public static DonacionDeDineroDTO toDonacionDeDineroDTO(DonacionDeDinero donacionDeDinero) {
        return DonacionDeDineroDTO.builder().
        id(donacionDeDinero.getId().toString()).
                monto(donacionDeDinero.getMonto().toString()).
                frecuencia(donacionDeDinero.getFrecuencia().toString()).
                build();
    }
}
