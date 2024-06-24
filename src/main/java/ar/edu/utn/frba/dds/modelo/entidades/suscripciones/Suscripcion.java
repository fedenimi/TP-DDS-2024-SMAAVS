package ar.edu.utn.frba.dds.modelo.entidades.suscripciones;

import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import lombok.Getter;

@Getter
public class Suscripcion{
    private Colaborador suscriptor;
    private Integer configurableN;
}
