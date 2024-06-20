package ar.edu.utn.frba.dds.domain.datosPersonas;

import lombok.Getter;

import java.util.List;

public class Tarjeta {
    private String codigo;
    @Getter private Integer usosMaximoBase;
    @Getter private Integer usosMaximoPorMenor;
    @Getter private List<Uso> usos;
}
