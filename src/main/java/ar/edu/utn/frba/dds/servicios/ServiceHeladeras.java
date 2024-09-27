package ar.edu.utn.frba.dds.servicios;

import ar.edu.utn.frba.dds.dtos.HeladeraDTO;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;

public class ServiceHeladeras {
    public static HeladeraDTO toHeladeraDTO(Heladera heladera) {

        return HeladeraDTO.builder().
                id(heladera.getId().toString()).
                nombre(heladera.getDireccion().getNombre_direccion()).
                latitud(heladera.getDireccion().getPunto().getLatitud().toString()).
                longitud(heladera.getDireccion().getPunto().getLongitud().toString()).
                direccion(heladera.getDireccion().getDireccion()).
                cantidadViandas(heladera.getStock().toString()).
                capacidad(heladera.getCapacidad().toString()).
                estado(heladera.getEstado().toString()).
                build();


    }
}
