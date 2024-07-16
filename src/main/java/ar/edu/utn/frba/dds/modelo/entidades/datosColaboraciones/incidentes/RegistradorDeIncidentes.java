package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.AlertadorDeTecnicos;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import lombok.Getter;

@Getter
public class RegistradorDeIncidentes {
    private static RegistradorDeIncidentes instance = null;
    public static RegistradorDeIncidentes getInstance() {
        if(instance == null)
            instance = new RegistradorDeIncidentes();
        return instance;
    }
    public void registrarIncidente(Estado estado, Heladera heladera) {
        this.notificarAlertaALaHeladera(estado, heladera);
        AlertadorDeTecnicos.getInstance().alertar(heladera);
    }
    public void notificarAlertaALaHeladera(Estado estado, Heladera heladera) {
        heladera.setEstado(estado);
    }
}
