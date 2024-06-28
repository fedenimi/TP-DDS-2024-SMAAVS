package ar.edu.utn.frba.dds.modelo.entidades.suscripciones.condiciones;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.CondicionSuscripcionHeladera;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.Suscripcion;

public class Desperfecto implements CondicionSuscripcionHeladera{
    @Override
    public Boolean debeEnviar(Heladera heladera, Suscripcion suscripcion) {
        return heladera.getEstado() != Estado.ACTIVA;
    }
}
