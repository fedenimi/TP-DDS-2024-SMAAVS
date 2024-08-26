package ar.edu.utn.frba.dds.modelo.entidades.suscripciones.condiciones;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.Suscripcion;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

public class QuedanNViandas implements CondicionSuscripcionHeladera {
    @Override
    public Boolean debeEnviar(Heladera heladera, Suscripcion suscripcion) {
        return heladera.getStock() <= suscripcion.getConfigurableN();
    }
}
