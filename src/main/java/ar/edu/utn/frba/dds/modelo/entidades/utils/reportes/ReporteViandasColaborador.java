package ar.edu.utn.frba.dds.modelo.entidades.utils.reportes;

import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DistribucionDeViandas;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DonacionDeViandas;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDistribucionesViandas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDonacionesViandas;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;



public class ReporteViandasColaborador implements Reporte {
    private RepositorioDonacionesViandas repositorioDonacionesViandas;
    private RepositorioDistribucionesViandas repositorioDistribucionesViandas;
    Map<Colaborador, Integer> colaboradorViandaMap;
    @Getter private String nombreArchivo;

    public ReporteViandasColaborador( RepositorioDonacionesViandas repositorioDonacionesViandas, RepositorioDistribucionesViandas repositorioDistribucionesViandas, String nombreArchivo) {
        this.repositorioDonacionesViandas = repositorioDonacionesViandas;
        this.repositorioDistribucionesViandas = repositorioDistribucionesViandas;
        this.nombreArchivo = nombreArchivo;
    }
    @Override
    public String crearReporte() {
        colaboradorViandaMap = new HashMap<Colaborador, Integer>();
        for (int i = 0; i < repositorioDonacionesViandas.getDonacionDeViandas().size(); i++) {
            DonacionDeViandas donacionDeViandas = repositorioDonacionesViandas.getDonacionDeViandas().get(i);
            if (this.esEstaSemana(donacionDeViandas.getFecha())){
                Colaborador colaborador = donacionDeViandas.getColaborador();
                Integer cantidadDeViandas = donacionDeViandas.cantidadDeViandas();
                this.agregarViandasAMap(cantidadDeViandas, colaborador);
            }
        }
        for (int i = 0; i < repositorioDistribucionesViandas.getDistribucionDeViandas().size(); i++) {
            DistribucionDeViandas distribucionDeViandas = repositorioDistribucionesViandas.getDistribucionDeViandas().get(i);
            if (this.esEstaSemana(distribucionDeViandas.getFecha())) {
                Colaborador colaborador = distribucionDeViandas.getColaborador();
                Integer cantidadDeViandas = distribucionDeViandas.getCantidadDeViandas();
                this.agregarViandasAMap(cantidadDeViandas, colaborador);
            }
        }
        StringBuilder result = new StringBuilder();
        result.append("Fecha: ")
                .append(LocalDate.now().toString())
                .append("\n");
        for (Map.Entry<Colaborador, Integer> entry : colaboradorViandaMap.entrySet()) {
            result.append(entry.getKey().getNombre())
                    .append(": ")
                    .append(entry.getValue())
                    .append(" viandas\n");
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

    private void agregarViandasAMap(Integer cantidadDeViandas, Colaborador colaborador) {
        if(colaboradorViandaMap.containsKey(colaborador) ) {
            cantidadDeViandas += colaboradorViandaMap.get(colaborador);
        }
        colaboradorViandaMap.put(colaborador, cantidadDeViandas);
    }
}
