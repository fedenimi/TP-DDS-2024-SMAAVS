package ar.edu.utn.frba.dds.modelo.entidades.suscripciones.condiciones;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.Suscripcion;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "desperfecto")
public class Desperfecto implements CondicionSuscripcionHeladera{
    @Id
    @GeneratedValue
    private Long id;
    @Override
    public Boolean debeEnviar(Heladera heladera, Suscripcion suscripcion) {
        return heladera.getEstado() != Estado.ACTIVA;
    }
}
