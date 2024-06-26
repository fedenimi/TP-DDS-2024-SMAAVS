package ar.edu.utn.frba.dds.modelo.entidades.utils.reportes;

import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDistribucionesViandas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDonacionesViandas;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
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
            Colaborador colaborador = repositorioDonacionesViandas.getDonacionDeViandas().get(i).getColaborador();
            Integer cantidadDeViandas = repositorioDonacionesViandas.getDonacionDeViandas().get(i).cantidadDeViandas();
            this.agregarViandasAMap(cantidadDeViandas, colaborador);

        }
        for (int i = 0; i < repositorioDistribucionesViandas.getDistribucionDeViandas().size(); i++) {
            Colaborador colaborador = repositorioDistribucionesViandas.getDistribucionDeViandas().get(i).getColaborador();
            Integer cantidadDeViandas = repositorioDistribucionesViandas.getDistribucionDeViandas().get(i).getCantidadDeViandas();
            this.agregarViandasAMap(cantidadDeViandas, colaborador);
        }
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Colaborador, Integer> entry : colaboradorViandaMap.entrySet()) {
            result.append(entry.getKey().getNombre())
                    .append(": ")
                    .append(entry.getValue())
                    .append(" viandas\n");
        }
        return result.toString();
    }

    private void agregarViandasAMap(Integer cantidadDeViandas, Colaborador colaborador) {
        if(colaboradorViandaMap.containsKey(colaborador) ) {
            cantidadDeViandas += colaboradorViandaMap.get(colaborador);
        }
        colaboradorViandaMap.put(colaborador, cantidadDeViandas);
    }
}
