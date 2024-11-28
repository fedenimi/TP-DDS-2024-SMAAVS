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
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ReporteViandasHeladera implements Reporte {
    private List<DonacionDeViandas> donacionesDeViandas;
    private List<DistribucionDeViandas> distribucionesDeViandas;
    @Getter private Map<Heladera, Integer> heladeraViandaColocadaMap;
    @Getter private Map<Heladera, Integer> heladeraViandaRetiradaMap;
    @Getter private String nombreArchivo;
    private ReporteStringViandasHeladera reporteStringViandasHeladera;

    public ReporteViandasHeladera( List<DonacionDeViandas> donacionDeViandas, List<DistribucionDeViandas> distribucionDeViandas, String nombreArchivo, ReporteStringViandasHeladera reporteStringViandasHeladera) {
        this.donacionesDeViandas = donacionDeViandas;
        this.distribucionesDeViandas = distribucionDeViandas;
        this.nombreArchivo = nombreArchivo;
        this.reporteStringViandasHeladera = reporteStringViandasHeladera;
    }
    @Override
    public String crearReporte() {
        heladeraViandaColocadaMap = new HashMap<Heladera, Integer>();
        heladeraViandaRetiradaMap = new HashMap<Heladera, Integer>();
        for (int i = 0; i < donacionesDeViandas.size(); i++) {
            DonacionDeViandas donacionDeViandas = donacionesDeViandas.get(i);
            if (CalculadorDeFechas.getInstance().esEstaSemana(donacionDeViandas.getFecha())) {
                Integer cantidadDeViandas = donacionDeViandas.cantidadDeViandas();
                for (int j = 0; j < cantidadDeViandas; j++) {
                    /*
                    Heladera heladera = donacionDeViandas.getViandasDonadas().get(j);
                    this.agregarViandasAMap(1, heladera, heladeraViandaColocadaMap);
                     */
                }
            }
        }
        for (int i = 0; i < distribucionesDeViandas.size(); i++) {
            DistribucionDeViandas distribucionDeViandas = distribucionesDeViandas.get(i);
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
