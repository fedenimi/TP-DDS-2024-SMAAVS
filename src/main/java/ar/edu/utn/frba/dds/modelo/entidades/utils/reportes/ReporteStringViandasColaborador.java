package ar.edu.utn.frba.dds.modelo.entidades.utils.reportes;

import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;

import java.time.LocalDate;
import java.util.Map;

public class ReporteStringViandasColaborador {
    public String crearReporteString(ReporteViandasColaborador reporteViandasColaborador) {
        StringBuilder result = new StringBuilder();
        result.append("Fecha: ")
                .append(LocalDate.now().toString())
                .append("\n");
        for (Map.Entry<Colaborador, Integer> entry : reporteViandasColaborador.getColaboradorViandaMap().entrySet()) {
            result.append(entry.getKey().getNombre())
                    .append(": ")
                    .append(entry.getValue())
                    .append(" viandas\n");
        }
        return result.toString();
    }
}
