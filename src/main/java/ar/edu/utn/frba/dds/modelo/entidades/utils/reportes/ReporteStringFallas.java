package ar.edu.utn.frba.dds.modelo.entidades.utils.reportes;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;

import java.time.LocalDate;
import java.util.Map;

public class ReporteStringFallas {
    public String crearReporteString(ReporteFallas reporteFallas) {
        StringBuilder result = new StringBuilder();
        result.append("Fecha: ")
                .append(LocalDate.now().toString())
                .append("\n");
        for (Map.Entry<Heladera, Integer> entry : reporteFallas.getHeladeraFallasMap().entrySet()) {
            result.append("Heladera: ")
                    .append(entry.getKey().getId())
                    .append(": ")
                    .append(entry.getValue())
                    .append(" viandas\n");
        }
        return result.toString();
    }
}
