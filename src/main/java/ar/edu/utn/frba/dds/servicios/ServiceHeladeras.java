package ar.edu.utn.frba.dds.servicios;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.dtos.HeladeraDTO;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.HacerseCargoDeHeladera;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.Puntuable;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioPuntuables;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
}