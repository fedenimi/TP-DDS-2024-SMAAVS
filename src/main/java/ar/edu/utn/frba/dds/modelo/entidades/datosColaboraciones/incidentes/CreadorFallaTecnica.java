package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;

import java.time.LocalDateTime;

public class CreadorFallaTecnica {

    private static CreadorFallaTecnica instance = null;
    public static CreadorFallaTecnica getInstance() {
        if(instance == null)
            instance = new CreadorFallaTecnica();
        return instance;
    }
    public FallaTecnica crearFallaTecnica(Heladera heladera, Colaborador reportador, String descripcion, String foto, LocalDateTime fechaYHora) {
        return new FallaTecnica(1L,reportador, descripcion, foto, fechaYHora, heladera);
    }

}

