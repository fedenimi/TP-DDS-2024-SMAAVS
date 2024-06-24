package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.AlertadorDeTecnicos;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.FactoryAlerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.IncidenteDO;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public abstract class Receptor {
    private Heladera heladera;
    public void registrarIncidente(IncidenteDO incidenteDO) {
        this.notificarAlertaALaHeladera(incidenteDO.getEstado());
        this.crearIncidente(heladera, incidenteDO);
        AlertadorDeTecnicos.getInstance().alertar(heladera);
    }
    public void notificarAlertaALaHeladera(Estado estado) {
        heladera.setEstado(estado);
    }

    public void crearIncidente(Heladera heladera, IncidenteDO incidenteDO) {
        Alerta alerta = FactoryAlerta.getInstance().crearIncidente(heladera, incidenteDO);
    }
}
