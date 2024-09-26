package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Tecnico;

public interface IBuscadorDeTecnicos {
    Tecnico buscarTecnicoMasCercanoA(Heladera heladera);
}
