package ar.edu.utn.frba.dds.modelo.entidades.utils.converters;

import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.condiciones.CondicionSuscripcionHeladera;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.condiciones.Desperfecto;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.condiciones.FaltanNViandas;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.condiciones.QuedanNViandas;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class CondicionSuscripcionHeladeraConverter implements AttributeConverter<CondicionSuscripcionHeladera, String> {
    @Override
    public String convertToDatabaseColumn(CondicionSuscripcionHeladera condicionSuscripcionHeladera) {
        String condicion = null;

        if (condicionSuscripcionHeladera.getClass().getName().equals("QuedanNViandas")) {
            condicion = "QuedanNViandas";
        }
        if (condicionSuscripcionHeladera.getClass().getName().equals("FaltanNViandas")) {
            condicion = "FaltanNViandas";
        }
        if (condicionSuscripcionHeladera.getClass().getName().equals("Desperfecto")) {
            condicion = "Desperfecto";
        }
        return condicion;
    }
    @Override
    public CondicionSuscripcionHeladera convertToEntityAttribute(String s) {
        CondicionSuscripcionHeladera condicion = null;
        if (s.equals("QuedanNViandas")) {
            condicion = new QuedanNViandas();
        }
        if (s.equals("FaltanNViandas")) {
            condicion = new FaltanNViandas();
        }
        if (s.equals("Desperfecto")) {
            condicion = new Desperfecto();
        }
        return condicion;
    }
}
