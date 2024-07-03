package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores.Receptor;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;

import java.util.List;

public class ReceptorFallaTecnica extends Receptor {
    private List<FallaTecnica> fallasTecnicas;

    public void recibirFallaTecnica(Colaborador reportador, String descripcion, String foto) {
        this.registrarIncidente(Estado.FALLA_TECNICA);
    }
    /*
    @Override
    public void crearAlerta(Heladera heladera, IncidenteDO incidenteDO){
        FallaTecnica fallaTecnica = FactoryFallaTecnica.getInstance().crearIncidente(heladera, incidenteDO);
    }
    */

}
