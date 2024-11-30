package ar.edu.utn.frba.dds.servicios;

import ar.edu.utn.frba.dds.dtos.ModeloDTO;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.OfrecerProducto;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Rubro;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.ModeloHeladera;
import com.itextpdf.forms.xfdf.Mode;

public class ServiceModelo {
    public static ModeloDTO toModeloDTO(ModeloHeladera modeloHeladera) {
        return ModeloDTO.
                builder().
                nombre(modeloHeladera.getNombre()).
                temperaturaMaxima(modeloHeladera.getTemperaturaMaxima().toString()).
                temperaturaMinima(modeloHeladera.getTemperaturaMinima().toString()).
                id(modeloHeladera.getId().toString()).
                build();
    }
}
