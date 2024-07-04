package ar.edu.utn.frba.dds.modelo.entidades.utils.reportes;

import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DistribucionDeViandas;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DonacionDeViandas;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.utils.CalculadorDeFechas;
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
    @Getter private Map<Heladera, Integer> heladeraViandaColocadaMap;
    @Getter private Map<Heladera, Integer> heladeraViandaRetiradaMap;
    @Getter private String nombreArchivo;
    private ReporteStringViandasHeladera reporteStringViandasHeladera;

    public ReporteViandasHeladera( RepositorioDonacionesViandas repositorioDonacionesViandas, RepositorioDistribucionesViandas repositorioDistribucionesViandas, String nombreArchivo, ReporteStringViandasHeladera reporteStringViandasHeladera) {
        this.repositorioDonacionesViandas = repositorioDonacionesViandas;
        this.repositorioDistribucionesViandas = repositorioDistribucionesViandas;
        this.nombreArchivo = nombreArchivo;
        this.reporteStringViandasHeladera = reporteStringViandasHeladera;
    }
    @Override
    public String crearReporte() {
        heladeraViandaColocadaMap = new HashMap<Heladera, Integer>();
        heladeraViandaRetiradaMap = new HashMap<Heladera, Integer>();
        for (int i = 0; i < repositorioDonacionesViandas.getDonacionDeViandas().size(); i++) {
            DonacionDeViandas donacionDeViandas = repositorioDonacionesViandas.getDonacionDeViandas().get(i);
            if (CalculadorDeFechas.getInstance().esEstaSemana(donacionDeViandas.getFecha())) {
                Integer cantidadDeViandas = donacionDeViandas.cantidadDeViandas();
                for (int j = 0; j < cantidadDeViandas; j++) {
                    Heladera heladera = donacionDeViandas.getViandasDonadas().get(j).getHeladera();
                    this.agregarViandasAMap(1, heladera, heladeraViandaColocadaMap);
                }
            }
        }
        for (int i = 0; i < repositorioDistribucionesViandas.getDistribucionDeViandas().size(); i++) {
            DistribucionDeViandas distribucionDeViandas = repositorioDistribucionesViandas.getDistribucionDeViandas().get(i);
            if (CalculadorDeFechas.getInstance().esEstaSemana(distribucionDeViandas.getFecha())) {
                this.agregarViandasAMap(distribucionDeViandas.getCantidadDeViandas(), distribucionDeViandas.getHeladeraDestino(), heladeraViandaColocadaMap);
                this.agregarViandasAMap(distribucionDeViandas.getCantidadDeViandas(), distribucionDeViandas.getHeladeraOrigen(), heladeraViandaRetiradaMap);
            }
        }
    return reporteStringViandasHeladera.crearReporteString(this);
    }

    private void agregarViandasAMap(Integer cantidadDeViandas, Heladera heladera, Map<Heladera, Integer> map) {
        if(map.containsKey(heladera) ) {
            cantidadDeViandas += map.get(heladera);
        }
        map.put(heladera, cantidadDeViandas);
    }
}
