package ar.edu.utn.frba.dds.modelo.entidades.suscripciones.condiciones;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.Suscripcion;

public interface CondicionSuscripcionHeladera {
    public Boolean debeEnviar(Heladera heladera, Suscripcion suscripcion);
}
