package ar.edu.utn.frba.dds.modelo.entidades.datosPersonas;

import lombok.Getter;

import java.util.List;

public class TarjetaPersonaVulnerable {
    private String codigo;
    @Getter private Integer usosMaximoBase;
    @Getter private Integer usosMaximoPorMenor;
    @Getter private List<Uso> usos;
}
