package ar.edu.utn.frba.dds.servicios;

import ar.edu.utn.frba.dds.dtos.RubroDTO;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.OfrecerProducto;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Rubro;


public class ServiceRubro {
    public static RubroDTO toRubroDTO(Rubro rubro) {
        return RubroDTO.
                builder().
                id(rubro.getId().toString()).
                nombre(rubro.getNombre()).
                build();
    }
}
