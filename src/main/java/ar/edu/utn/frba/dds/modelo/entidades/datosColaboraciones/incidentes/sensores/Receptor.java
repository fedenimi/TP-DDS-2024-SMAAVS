package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.AlertadorDeTecnicos;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.FactoryAlerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;

import java.time.LocalDateTime;

public abstract class Receptor {
    private Heladera heladera;
    private FactoryAlerta factoryAlerta;

    public void registrarAlerta(Estado estado) {
        this.notificarAlerta(estado);
        Alerta alerta = factoryAlerta.crearAlerta(estado, LocalDateTime.now(), heladera);
        AlertadorDeTecnicos.getInstance().alertar(heladera);
    }

    public void notificarAlerta(Estado estado) {
        heladera.setEstado(estado);
    }
}
