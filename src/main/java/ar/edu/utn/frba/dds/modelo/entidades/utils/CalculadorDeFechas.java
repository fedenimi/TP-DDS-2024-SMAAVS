package ar.edu.utn.frba.dds.modelo.entidades.utils;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.AlertadorDeTecnicos;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class CalculadorDeFechas {
    private static CalculadorDeFechas instance = null;
    public static CalculadorDeFechas getInstance() {
        if(instance == null)
            instance = new CalculadorDeFechas();
        return instance;
    }

    public boolean esEstaSemana(LocalDate fecha) {
        LocalDate today = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());

        int currentWeek = today.get(weekFields.weekOfWeekBasedYear());
        int dateWeek = fecha.get(weekFields.weekOfWeekBasedYear());

        int currentYear = today.get(weekFields.weekBasedYear());
        int dateYear = fecha.get(weekFields.weekBasedYear());

        return currentWeek == dateWeek && currentYear == dateYear;
    }
}
