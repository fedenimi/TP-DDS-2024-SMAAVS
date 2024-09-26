package ar.edu.utn.frba.dds.servicios;

import ar.edu.utn.frba.dds.dtos.HeladeraDTO;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;

public class ServiceHeladeras {
    public static HeladeraDTO toHeladeraDTO(Heladera heladera) {
        return HeladeraDTO.builder().
                id(heladera.getId().toString()).
                direccion(heladera.getDireccion()).
                //cantViandas(heladera.getCantViandas().toString()). TODO ver como sacar viandas
                capacidad(heladera.getCapacidad().toString())
                .build();
    }
}
