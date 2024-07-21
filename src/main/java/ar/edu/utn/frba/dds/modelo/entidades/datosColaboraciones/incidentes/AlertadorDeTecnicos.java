package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.MedioDeContacto;
import ar.edu.utn.frba.dds.modelo.entidades.enviadores.Llamador;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Tecnico;

import java.util.ArrayList;
import java.util.List;

public class AlertadorDeTecnicos {

    private static AlertadorDeTecnicos instance = null;
    public static AlertadorDeTecnicos getInstance() {
        if(instance == null)
            instance = new AlertadorDeTecnicos();
        return instance;
    }
    public void alertar(Heladera heladera, IBuscadorDeTecnicos buscadorDeTecnicos) {
        Tecnico tecnicoMasCercano = buscadorDeTecnicos.buscarTecnicoMasCercanoA(heladera);
        List<MedioDeContacto> mediosDeContacto = new ArrayList<>();
        mediosDeContacto.add(tecnicoMasCercano.getMedioDeContacto());
        Llamador.getInstance().llamar(mediosDeContacto, "Anda a arreglar la heladera " + heladera.getId(), "HELADERA ROTA");
    }
}
