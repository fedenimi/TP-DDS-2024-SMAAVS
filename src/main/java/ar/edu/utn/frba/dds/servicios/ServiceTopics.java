package ar.edu.utn.frba.dds.servicios;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.Topic;

import java.util.Optional;

public class ServiceTopics {
    public void accionarTopic(Heladera heladera, String idTopic) {
        Optional<Topic> topicOptional = heladera.getTopics().stream().filter(t -> t.getId().equals(idTopic)).findFirst();
        if (topicOptional.isPresent()) topicOptional.get().notificarSuscriptores(heladera);
    }
}
