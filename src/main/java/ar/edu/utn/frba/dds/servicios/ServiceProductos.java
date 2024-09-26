package ar.edu.utn.frba.dds.servicios;

import ar.edu.utn.frba.dds.dtos.ProductoDTO;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.OfrecerProducto;
import lombok.Getter;
import lombok.Setter;


public class ServiceProductos {
    public static ProductoDTO toProductoDTO(OfrecerProducto producto) {
        return ProductoDTO.
                builder().
                id(Float.toString(producto.getId())).
                puntaje(Double.toString(producto.getOferta().getPuntajeMinimo())).
                nombre(producto.getOferta().getNombre()).
                rubro(producto.getOferta().getRubro().getNombre()).build();
    }
}
