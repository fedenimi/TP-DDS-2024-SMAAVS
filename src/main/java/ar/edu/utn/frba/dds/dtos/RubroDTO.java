package ar.edu.utn.frba.dds.dtos;

import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.OfrecerProducto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class RubroDTO {
    private String id;
    private String nombre;
    private List<OfrecerProductoDTO> productos;
}
