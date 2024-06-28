package ar.edu.utn.frba.dds.modelo.entidades.utils.reportes;

import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DistribucionDeViandas;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DonacionDeViandas;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDistribucionesViandas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDonacionesViandas;
import lombok.Getter;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ReporteViandasHeladera implements Reporte {
    private RepositorioDonacionesViandas repositorioDonacionesViandas;
    private RepositorioDistribucionesViandas repositorioDistribucionesViandas;
    Map<Heladera, Integer> heladeraViandaColocadaMap;
    Map<Heladera, Integer> heladeraViandaRetiradaMap;
    @Getter private String nombreArchivo;

    public ReporteViandasHeladera( RepositorioDonacionesViandas repositorioDonacionesViandas, RepositorioDistribucionesViandas repositorioDistribucionesViandas, String nombreArchivo) {
        this.repositorioDonacionesViandas = repositorioDonacionesViandas;
        this.repositorioDistribucionesViandas = repositorioDistribucionesViandas;
        this.nombreArchivo = nombreArchivo;
    }
    @Override
    public String crearReporte() {
        heladeraViandaColocadaMap = new HashMap<Heladera, Integer>();
        heladeraViandaRetiradaMap = new HashMap<Heladera, Integer>();
        for (int i = 0; i < repositorioDonacionesViandas.getDonacionDeViandas().size(); i++) {
            DonacionDeViandas donacionDeViandas = repositorioDonacionesViandas.getDonacionDeViandas().get(i);
            if (this.esEstaSemana(donacionDeViandas.getFecha())) {
                Integer cantidadDeViandas = donacionDeViandas.cantidadDeViandas();
                for (int j = 0; j < cantidadDeViandas; j++) {
                    Heladera heladera = donacionDeViandas.getViandasDonadas().get(j).getHeladera();
                    this.agregarViandasAMap(1, heladera, heladeraViandaColocadaMap);
                }
            }
        }
        for (int i = 0; i < repositorioDistribucionesViandas.getDistribucionDeViandas().size(); i++) {
            DistribucionDeViandas distribucionDeViandas = repositorioDistribucionesViandas.getDistribucionDeViandas().get(i);
            if (this.esEstaSemana(distribucionDeViandas.getFecha())) {
                this.agregarViandasAMap(distribucionDeViandas.getCantidadDeViandas(), distribucionDeViandas.getHeladeraDestino(), heladeraViandaColocadaMap);
                this.agregarViandasAMap(distribucionDeViandas.getCantidadDeViandas(), distribucionDeViandas.getHeladeraOrigen(), heladeraViandaRetiradaMap);
            }
        }
        StringBuilder result = new StringBuilder();
        result.append("Fecha: ")
                .append(LocalDate.now().toString())
                .append("\n");
        Map<Heladera, Integer> allHeladeras = new HashMap<>(heladeraViandaRetiradaMap);
        allHeladeras.putAll(heladeraViandaColocadaMap);

        for (Heladera heladera : allHeladeras.keySet()) {
            int colocadas = heladeraViandaColocadaMap.getOrDefault(heladera, 0);
            int retiradas = heladeraViandaRetiradaMap.getOrDefault(heladera, 0);
            result.append("Heladera ")
                    .append(heladera.getId())
                    .append(": ")
                    .append(colocadas)
                    .append(" colocadas y ")
                    .append(retiradas)
                    .append(" retiradas\n");
        }
        return result.toString();
    }

    private boolean esEstaSemana(LocalDate fecha) {
        LocalDate today = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());

        int currentWeek = today.get(weekFields.weekOfWeekBasedYear());
        int dateWeek = fecha.get(weekFields.weekOfWeekBasedYear());

        int currentYear = today.get(weekFields.weekBasedYear());
        int dateYear = fecha.get(weekFields.weekBasedYear());

        return currentWeek == dateWeek && currentYear == dateYear;
    }
    private void agregarViandasAMap(Integer cantidadDeViandas, Heladera heladera, Map<Heladera, Integer> map) {
        if(map.containsKey(heladera) ) {
            cantidadDeViandas += map.get(heladera);
        }
        map.put(heladera, cantidadDeViandas);
    }
}
