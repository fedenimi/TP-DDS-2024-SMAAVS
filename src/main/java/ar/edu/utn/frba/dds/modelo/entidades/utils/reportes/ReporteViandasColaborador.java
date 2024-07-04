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
import java.util.Locale;
import java.util.Map;



public class ReporteViandasColaborador implements Reporte {
    private RepositorioDonacionesViandas repositorioDonacionesViandas;
    private RepositorioDistribucionesViandas repositorioDistribucionesViandas;
    @Getter private Map<Colaborador, Integer> colaboradorViandaMap;
    @Getter private String nombreArchivo;
    private ReporteStringViandasColaborador reporteStringViandasColaborador;

    public ReporteViandasColaborador( RepositorioDonacionesViandas repositorioDonacionesViandas, RepositorioDistribucionesViandas repositorioDistribucionesViandas, String nombreArchivo, ReporteStringViandasColaborador reporteStringViandasColaborador) {
        this.repositorioDonacionesViandas = repositorioDonacionesViandas;
        this.repositorioDistribucionesViandas = repositorioDistribucionesViandas;
        this.nombreArchivo = nombreArchivo;
        this.reporteStringViandasColaborador = reporteStringViandasColaborador;
    }
    @Override
    public String crearReporte() {
        colaboradorViandaMap = new HashMap<Colaborador, Integer>();
        for (int i = 0; i < repositorioDonacionesViandas.getDonacionDeViandas().size(); i++) {
            DonacionDeViandas donacionDeViandas = repositorioDonacionesViandas.getDonacionDeViandas().get(i);
            if (CalculadorDeFechas.getInstance().esEstaSemana(donacionDeViandas.getFecha())){
                Colaborador colaborador = donacionDeViandas.getColaborador();
                Integer cantidadDeViandas = donacionDeViandas.cantidadDeViandas();
                this.agregarViandasAMap(cantidadDeViandas, colaborador);
            }
        }
        for (int i = 0; i < repositorioDistribucionesViandas.getDistribucionDeViandas().size(); i++) {
            DistribucionDeViandas distribucionDeViandas = repositorioDistribucionesViandas.getDistribucionDeViandas().get(i);
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
