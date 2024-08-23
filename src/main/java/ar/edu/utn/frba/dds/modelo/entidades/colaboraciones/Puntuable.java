package ar.edu.utn.frba.dds.modelo.entidades.colaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

public interface Puntuable {
    float puntaje();
    float getMultiplicador();

    Colaborador getColaborador();
    int cantidadDeMesesSiendoHeladera();
}
