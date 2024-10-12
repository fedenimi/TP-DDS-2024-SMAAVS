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
        if (condicionSuscripcionHeladera instanceof FaltanNViandas) {
            int cantidad = heladera.getCapacidad() - heladera.getStock();
            return AlertaSuscripcion.builder().
              suscripcionHumana(ServiceSuscripcionesHumanas.suscripcionHumanaDe(suscriptor, heladera, TipoNotificacion.FALTAN_N_VIANDAS)).
              descripcion_alerta("Faltan" + cantidad + "viandas para que se llene la heladera").
              build();

        } else if (condicionSuscripcionHeladera instanceof QuedanNViandas) {
            return AlertaSuscripcion.builder().
                suscripcionHumana(ServiceSuscripcionesHumanas.suscripcionHumanaDe(suscriptor, heladera, TipoNotificacion.QUEDAN_N_VIANDAS)).
                descripcion_alerta("Quedan solamente" + heladera.getStock() + "viandas en la heladera").
                build();

        } else if (condicionSuscripcionHeladera instanceof Desperfecto) {
            return AlertaSuscripcion.builder().
                suscripcionHumana(ServiceSuscripcionesHumanas.suscripcionHumanaDe(suscriptor, heladera, TipoNotificacion.DESPERFECTO)).
                descripcion_alerta(ServiceHeladeras.presentarDistribucionesPosiblesPara(heladera)).
                build();
        }
        /*
        descripcion_alerta("La heladera presenta un desperfecto de tipo " + heladera.getEstado().toString() + ". \n" +
                        ServiceHeladeras.presentarDistribucionesPosiblesPara(heladera)).
         */
        return null;
    }


}
