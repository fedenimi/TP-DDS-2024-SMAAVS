package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.AlertadorDeTecnicos;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.FactoryAlerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.IncidenteDO;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import lombok.Getter;

@Getter
public abstract class Receptor {
    private Heladera heladera;
    public void registrarIncidente(Estado estado) {
        this.notificarAlertaALaHeladera(estado);
        AlertadorDeTecnicos.getInstance().alertar(heladera);
    }
    public void notificarAlertaALaHeladera(Estado estado) {
        heladera.setEstado(estado);
    }

    public Alerta crearAlerta(Heladera heladera, IncidenteDO incidenteDO) {
        return FactoryAlerta.getInstance().crearIncidente(heladera, incidenteDO);
    }
}
