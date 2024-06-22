package ar.edu.utn.frba.dds.entidades.utils;

import ar.edu.utn.frba.dds.entidades.datosPersonas.MedioDeContacto;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ColaboradorDO {
    String tipoDoc;
    String doc;
    String nombre;
    String apellido;
    MedioDeContacto medioDeContacto;
    String fechaColaboracion;
    String formaColaboracion;
    int cantidad;

    public static ColaboradorDO of(String tipoDoc, String doc, String nombre, String apellido, MedioDeContacto medioDeContacto, String fechaColaboracion, String formaColaboracion, int cantidad){
        return ColaboradorDO
                .builder()
                .tipoDoc(tipoDoc)
                .doc(doc)
                .nombre(nombre)
                .apellido(apellido)
                .medioDeContacto(medioDeContacto)
                .fechaColaboracion(fechaColaboracion)
                .formaColaboracion(formaColaboracion)
                .cantidad(cantidad)
                .build();
    }

}
