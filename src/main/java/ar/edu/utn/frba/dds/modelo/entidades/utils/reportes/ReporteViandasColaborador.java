package ar.edu.utn.frba.dds.modelo.entidades.utils.reportes;

import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DistribucionDeViandas;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DonacionDeViandas;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.utils.CalculadorDeFechas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDistribucionesViandas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDonacionesViandas;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;



public class ReporteViandasColaborador implements Reporte {
    private List<DonacionDeViandas> donacionesDeViandas;
    private List<DistribucionDeViandas> distribucionesDeViandas;
    @Getter private Map<Colaborador, Integer> colaboradorViandaMap;
    @Getter private String nombreArchivo;
    private ReporteStringViandasColaborador reporteStringViandasColaborador;
    private DonacionDeViandas donacionDeViandas;

    public ReporteViandasColaborador(List<DonacionDeViandas> donacionDeViandas, List<DistribucionDeViandas> distribucionDeViandas, String nombreArchivo, ReporteStringViandasColaborador reporteStringViandasColaborador) {
        this.distribucionesDeViandas = distribucionDeViandas;
        this.donacionesDeViandas = donacionDeViandas;
        this.nombreArchivo = nombreArchivo;
        this.reporteStringViandasColaborador = reporteStringViandasColaborador;
    }
    @Override
    public String crearReporte() {
        colaboradorViandaMap = new HashMap<Colaborador, Integer>();
        for (int i = 0; i < donacionesDeViandas.size(); i++) {
            DonacionDeViandas donacionDeViandas = donacionesDeViandas.get(i);
            if (CalculadorDeFechas.getInstance().esEstaSemana(donacionDeViandas.getFecha())) {
                Colaborador colaborador = donacionDeViandas.getColaborador();
                Integer cantidadDeViandas = donacionDeViandas.cantidadDeViandas();
                this.agregarViandasAMap(cantidadDeViandas, colaborador);
            }
        }
        for (int i = 0; i < distribucionesDeViandas.size(); i++) {
            DistribucionDeViandas distribucionDeViandas =distribucionesDeViandas.get(i);
            if (CalculadorDeFechas.getInstance().esEstaSemana(distribucionDeViandas.getFecha())) {
                Colaborador colaborador = distribucionDeViandas.getColaborador();
                Integer cantidadDeViandas = distribucionDeViandas.getCantidadDeViandas();
                this.agregarViandasAMap(cantidadDeViandas, colaborador);
            }
        }
        return reporteStringViandasColaborador.crearReporteString(this);
    }
    private void agregarViandasAMap(Integer cantidadDeViandas, Colaborador colaborador) {
        if(colaboradorViandaMap.containsKey(colaborador) ) {
            cantidadDeViandas += colaboradorViandaMap.get(colaborador);
        }
        colaboradorViandaMap.put(colaborador, cantidadDeViandas);
    }
}
