package ar.edu.utn.frba.dds.modelo.entidades.suscripciones;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.enviadores.Llamador;
import lombok.Getter;

import java.util.List;

public class Topic {
    private List<Suscripcion> suscripciones;
    private String mensaje;
    private CondicionSuscripcionHeladera condicionSuscripcionHeladera;
    @Getter private String id;
    public void notificarSuscriptores(Heladera heladera) {
        suscripciones.forEach(suscripcion -> {
                if (condicionSuscripcionHeladera.debeEnviar(heladera, suscripcion)){
                    Llamador.getInstance().llamar(suscripcion.getSuscriptor().getMediosDeContacto(),mensaje, "HELADERA: " + heladera.getId());
                }
            }
        );
    }
}
