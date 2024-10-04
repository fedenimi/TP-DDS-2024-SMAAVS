package ar.edu.utn.frba.dds.servicios;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.AlertaSuscripcion;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.TipoNotificacion;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.Topic;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.condiciones.CondicionSuscripcionHeladera;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.condiciones.Desperfecto;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.condiciones.FaltanNViandas;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.condiciones.QuedanNViandas;

import java.util.Optional;

public class ServiceTopics {
    public static void accionarTopic(Heladera heladera, TipoNotificacion tipoNotificacion) {
            Topic unTopic = obtenerTopicDe(heladera, tipoNotificacion);
            unTopic.notificarSuscriptores(heladera);
    }

    public static Topic obtenerTopicDe(Heladera heladera, TipoNotificacion tipoNotificacion) {
        Optional<Topic> topic = Optional.empty();
        switch (tipoNotificacion) {
            case FALTAN_N_VIANDAS:
                topic = heladera.getTopics().stream().filter(t -> t.getCondicionSuscripcionHeladera() instanceof FaltanNViandas).findFirst();
                break;
            case QUEDAN_N_VIANDAS:
                topic = heladera.getTopics().stream().filter(t -> t.getCondicionSuscripcionHeladera() instanceof QuedanNViandas).findFirst();
                break;
            case DESPERFECTO:
                topic = heladera.getTopics().stream().filter(t -> t.getCondicionSuscripcionHeladera() instanceof Desperfecto).findFirst();
                break;
        }
        return topic.orElse(null);
    }

    public static AlertaSuscripcion alertaSuscripcionPara(Colaborador suscriptor, Heladera heladera, CondicionSuscripcionHeladera condicionSuscripcionHeladera) {
        AlertaSuscripcion.AlertaSuscripcionBuilder alertaSuscripcionBuilder = AlertaSuscripcion.builder().heladera(heladera);
        if (condicionSuscripcionHeladera instanceof FaltanNViandas) {
            alertaSuscripcionBuilder.tipoNotificacion(TipoNotificacion.FALTAN_N_VIANDAS)
                    .cantidad(heladera.getCapacidad() - heladera.getStock());
        } else if (condicionSuscripcionHeladera instanceof QuedanNViandas) {
            alertaSuscripcionBuilder.tipoNotificacion(TipoNotificacion.QUEDAN_N_VIANDAS)
                    .cantidad(heladera.getStock());
        } else if (condicionSuscripcionHeladera instanceof Desperfecto) {
            alertaSuscripcionBuilder.tipoNotificacion(TipoNotificacion.DESPERFECTO);
        }
        return alertaSuscripcionBuilder.build();

    }


}
