package ar.edu.utn.frba.dds.modelo.entidades.utils.reportes;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class ReporteStringViandasHeladera{

    public String crearReporteString(ReporteViandasHeladera reporteViandasHeladera) {
        StringBuilder result = new StringBuilder();
        result.append("Fecha: ")
                .append(LocalDate.now().toString())
                .append("\n");
        Map<Heladera, Integer> allHeladeras = new HashMap<>(reporteViandasHeladera.getHeladeraViandaRetiradaMap());
        allHeladeras.putAll(reporteViandasHeladera.getHeladeraViandaColocadaMap());

        for (Heladera heladera : allHeladeras.keySet()) {
            int colocadas = reporteViandasHeladera.getHeladeraViandaColocadaMap().getOrDefault(heladera, 0);
            int retiradas = reporteViandasHeladera.getHeladeraViandaRetiradaMap().getOrDefault(heladera, 0);
            result.append("Heladera ")
                    .append(heladera.getId().toString())
                    .append(": ")
                    .append(colocadas)
                    .append(" colocadas y ")
                    .append(retiradas)
                    .append(" retiradas\n");
        }
        return result.toString();
    }
}
