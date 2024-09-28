package ar.edu.utn.frba.dds.servicios;

import ar.edu.utn.frba.dds.dtos.OfrecerProductoDTO;
import ar.edu.utn.frba.dds.dtos.RubroDTO;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.OfrecerProducto;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Rubro;

public class ServiceProducto {
    public static OfrecerProductoDTO toOfrecerProductoDTO(OfrecerProducto ofrecerProducto) {
        return OfrecerProductoDTO.
                builder().
                nombre(ofrecerProducto.getOferta().getNombre()).
                puntaje(ofrecerProducto.getOferta().getPuntajeMinimo().toString()).
                rubro(ofrecerProducto.getOferta().getRubro().getNombre()).
                build();
    }
}
