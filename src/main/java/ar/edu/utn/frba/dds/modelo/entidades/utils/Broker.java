package ar.edu.utn.frba.dds.modelo.entidades.utils;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.FallaConexion;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.FallaTecnica;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.ReceptorFallaConexion;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.ReceptorFallaTecnica;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores.SensorMovimiento;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores.SensorTemperatura;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.SolicitudApertura;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;

import java.time.LocalDateTime;

public class Broker {

    public void enviarSolicitud(Heladera heladera, Colaborador colaborador) {
        SolicitudApertura solicitudApertura = new SolicitudApertura(colaborador, LocalDateTime.now());
        heladera.agregarSolicitudApertura(solicitudApertura);
    }

    public void enviarTemperatura(String temperatura, SensorTemperatura sensorTemperatura) {
        sensorTemperatura.recibirDato(temperatura);
    }

    public void enviarMovimiento(SensorMovimiento sensorMovimiento) {
        sensorMovimiento.recibirDato();
    }

    public void enviarFallaConexion(ReceptorFallaConexion receptorFallaConexion) {
        receptorFallaConexion.recibirDato();
    }

    public void enviarFallaTecnica(ReceptorFallaTecnica receptorFallaTecnica, Colaborador reportador, String descripcion, String foto) {
        receptorFallaTecnica.recibirFallaTecnica(reportador, descripcion, foto);
    }
}
