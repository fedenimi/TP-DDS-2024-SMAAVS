package ar.edu.utn.frba.dds.servicios;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.dtos.HeladeraDTO;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.HacerseCargoDeHeladera;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.Puntuable;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioPuntuables;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class ServiceHeladeras {
    public static HeladeraDTO toHeladeraDTO(Heladera heladera) {

        return HeladeraDTO.builder().
                id(heladera.getId().toString()).
                nombre(heladera.getDireccion().getNombre_direccion()).
                latitud(heladera.getDireccion().getPunto().getLatitud().toString()).
                longitud(heladera.getDireccion().getPunto().getLongitud().toString()).
                direccion(heladera.getDireccion().getDireccion()).
                cantidadViandas(heladera.getStock().toString()).
                capacidad(heladera.getCapacidad().toString()).
                estado(heladera.getEstado().toString()).
                build();
    }

    public static Colaborador colaboradorDe(Heladera heladera) {
        RepositorioPuntuables repositorioPuntuables = ServiceLocator.instanceOf(RepositorioPuntuables.class);
        List<Puntuable> puntuablesHeladeras = repositorioPuntuables.buscarTodos().stream().filter(puntuable -> puntuable instanceof HacerseCargoDeHeladera).toList();
        List<HacerseCargoDeHeladera> hacerseCargoDeHeladeras = puntuablesHeladeras.stream().map(puntuable -> (HacerseCargoDeHeladera) puntuable).toList();
        Optional<HacerseCargoDeHeladera> colaboracion = hacerseCargoDeHeladeras.stream().filter(colab-> colab.getHeladera().equals(heladera)).findFirst();

        if(colaboracion.isEmpty()) return null;
        return colaboracion.get().getColaborador();
    }

    public static String presentarDistribucionesPosiblesPara(Heladera heladera) {
        RepositorioHeladeras repositorioHeladeras = ServiceLocator.instanceOf(RepositorioHeladeras.class);
        List<Heladera> heladerasMasCercanas =  heladerasCercanasA(heladera).subList(0, Math.min(2, heladerasCercanasA(heladera).size()-1));
        StringBuilder presentacion = new StringBuilder("Distribuciones posibles para la heladera: \n");
                for(int i = 0; i < heladerasMasCercanas.size(); i++) {
                    Heladera heladeraCercana = heladerasMasCercanas.get(i);
                    Integer viandasPosibles = heladeraCercana.getCapacidad() - heladeraCercana.getStock();
                 presentacion.append("A ").append(heladeraCercana.getDireccion().getNombre_direccion()).append(" pueden llevarse ").append(viandasPosibles).append(" viandas \n");
                }
            return presentacion.toString();
    }

    public static List<Heladera> heladerasCercanasA(Heladera heladera) {
        RepositorioHeladeras repositorioHeladeras = ServiceLocator.instanceOf(RepositorioHeladeras.class);
        Double latitudHeladera = heladera.getDireccion().getPunto().getLatitud();
        Double longitudHeladera = heladera.getDireccion().getPunto().getLongitud();
        List<Heladera> heladeras = repositorioHeladeras.buscarTodos();

        return heladeras.stream().filter(
                hel -> coordenadasCercanas(latitudHeladera, longitudHeladera,
                        hel.getDireccion().getPunto().getLatitud(),
                        hel.getDireccion().getPunto().getLongitud())).toList();
    }

    public static boolean coordenadasCercanas(Double latitud1, Double longitud1, Double latitud2, Double longitud2) {
        return Math.abs(latitud1 - latitud2) < 0.01 && Math.abs(longitud1 - longitud2) < 0.015;
    }
}