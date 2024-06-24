package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.AlertadorDeTecnicos;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.FactoryAlerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public abstract class Receptor {
    private Heladera heladera;
    private FactoryAlerta factoryAlerta;

    public void registrarAlerta(Estado estado) {
        this.notificarAlertaALaHeladera(estado);
        Alerta alerta = factoryAlerta.crearAlerta(estado, LocalDateTime.now(), heladera);
        AlertadorDeTecnicos.getInstance().alertar(heladera);
    }

    public void notificarAlertaALaHeladera(Estado estado) {
        heladera.setEstado(estado);
    }
}
