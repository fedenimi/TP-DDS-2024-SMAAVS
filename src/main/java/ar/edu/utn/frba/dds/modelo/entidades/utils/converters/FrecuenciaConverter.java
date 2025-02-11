package ar.edu.utn.frba.dds.modelo.entidades.utils.converters;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.frecuencia.Anual;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.frecuencia.Frecuencia;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.frecuencia.Mensual;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.frecuencia.Unica;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.condiciones.CondicionSuscripcionHeladera;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class FrecuenciaConverter implements AttributeConverter<Frecuencia, String> {
    @Override
    public String convertToDatabaseColumn(Frecuencia frecuencia) {
        String frecuenciaString = null;
        if (frecuencia instanceof Anual) {
           frecuenciaString = "Anual";
        }
        if (frecuencia instanceof Mensual) {
            frecuenciaString = "Mensual";
        }
        if (frecuencia instanceof Unica) {
            frecuenciaString = "Unica";
        }
        return frecuenciaString;
    }

    @Override
    public Frecuencia convertToEntityAttribute(String s) {
        Frecuencia frecuencia = null;
        if (s.equals("Anual")) {
            frecuencia = new Anual();
        }
        if (s.equals("Mensual")) {
            frecuencia = new Mensual();
        }
        if (s.equals("Unica")) {
            frecuencia = new Unica();
        }
        return frecuencia;
    }
}
