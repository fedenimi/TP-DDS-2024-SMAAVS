package ar.edu.utn.frba.dds.modelo.entidades.suscripciones;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;

public interface CondicionSuscripcionHeladera {
    public Boolean debeEnviar(Heladera heladera, Suscripcion suscripcion);
}
