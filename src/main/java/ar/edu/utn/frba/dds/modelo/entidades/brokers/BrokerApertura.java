package ar.edu.utn.frba.dds.modelo.entidades.brokers;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Apertura;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.SolicitudApertura;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;

import java.time.LocalDateTime;

public class BrokerApertura {

    public void enviarSolicitud(Heladera heladera, Colaborador colaborador) {
        SolicitudApertura solicitudApertura = new SolicitudApertura(colaborador, LocalDateTime.now());
        heladera.agregarSolicitudApertura(solicitudApertura);
    }

    public void abrir(Heladera heladera, Colaborador colaborador) {
        Apertura apertura = new Apertura(colaborador, LocalDateTime.now());
        heladera.validarApertura(apertura);
    }
}
