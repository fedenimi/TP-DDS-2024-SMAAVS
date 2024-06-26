package ar.edu.utn.frba.dds.modelo.entidades.utils.reportes;

import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DonacionDeViandas;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDistribucionesViandas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioDonacionesViandas;
import lombok.Getter;

import java.util.HashMap;
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
            Integer cantidadDeViandas = donacionDeViandas.cantidadDeViandas();
            for (int j = 0; j < cantidadDeViandas; j++) {
                Heladera heladera = donacionDeViandas.getViandasDonadas().get(j).getHeladera();
                this.agregarViandasAMap(1, heladera, heladeraViandaColocadaMap);
            }
        }
        for (int i = 0; i < repositorioDistribucionesViandas.getDistribucionDeViandas().size(); i++) {
            Heladera heladeraDestino = repositorioDistribucionesViandas.getDistribucionDeViandas().get(i).getHeladeraDestino();
            Heladera heladeraOrigen = repositorioDistribucionesViandas.getDistribucionDeViandas().get(i).getHeladeraOrigen();
            Integer cantidadDeViandas = repositorioDistribucionesViandas.getDistribucionDeViandas().get(i).getCantidadDeViandas();
            this.agregarViandasAMap(cantidadDeViandas, heladeraDestino, heladeraViandaColocadaMap);
            this.agregarViandasAMap(cantidadDeViandas, heladeraOrigen, heladeraViandaRetiradaMap);
        }
        StringBuilder result = new StringBuilder();
        Map<Heladera, Integer> allHeladeras = new HashMap<>(heladeraViandaRetiradaMap);
        allHeladeras.putAll(heladeraViandaColocadaMap);

        // Iterar sobre las claves de los mapas combinados
        for (Heladera heladera : allHeladeras.keySet()) {
            int colocadas = heladeraViandaColocadaMap.getOrDefault(heladera, 0);
            int retiradas = heladeraViandaRetiradaMap.getOrDefault(heladera, 0);

            // Construir la cadena de texto
            result.append(heladera.getId())
                    .append(": ")
                    .append(colocadas)
                    .append(" colocadas y ")
                    .append(retiradas)
                    .append(" retiradas\n");
        }
        return result.toString();
    }

    private void agregarViandasAMap(Integer cantidadDeViandas, Heladera heladera, Map<Heladera, Integer> map) {
        if(map.containsKey(heladera) ) {
            cantidadDeViandas += map.get(heladera);
        }
        map.put(heladera, cantidadDeViandas);
    }
}
