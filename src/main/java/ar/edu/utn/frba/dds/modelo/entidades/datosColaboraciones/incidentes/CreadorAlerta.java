package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;

import java.time.LocalDateTime;

public class CreadorAlerta {

    private static CreadorAlerta instance = null;
    public static CreadorAlerta getInstance() {
        if(instance == null)
            instance = new CreadorAlerta();
        return instance;
    }
    public Alerta crearAlerta(Heladera heladera, Estado estado) {
        return Alerta.builder().tipoAlerta(estado).fechaYHora(LocalDateTime.now()).heladera(heladera).build();
    }

}
