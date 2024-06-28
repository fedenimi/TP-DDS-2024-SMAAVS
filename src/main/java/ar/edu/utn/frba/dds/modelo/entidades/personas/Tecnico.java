package ar.edu.utn.frba.dds.modelo.entidades.personas;

import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.AreaDeCobertura;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.Documento;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.MedioDeContacto;
import lombok.Getter;

@Getter
public class Tecnico {
    private String nombre;
    private String apellido;
    private Documento documento;
    private String cuil;
    private MedioDeContacto medioDeContacto;
    private AreaDeCobertura areaDeCobertura;
}
