package ar.edu.utn.frba.dds.servicios;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.dtos.HeladeraDTO;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.HacerseCargoDeHeladera;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.Puntuable;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.ModeloHeladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.VisitaTecnica;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.Direccion;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.Punto;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.Topic;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.condiciones.Desperfecto;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.condiciones.FaltanNViandas;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.condiciones.QuedanNViandas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioModelos;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioPuntuables;
import io.javalin.http.Context;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class ServiceHeladeras {
    public static HeladeraDTO toHeladeraDTO(Heladera heladera) {

         HeladeraDTO.HeladeraDTOBuilder heladeraDTOBuilder = HeladeraDTO.builder().
                id(heladera.getId().toString()).
                nombre(heladera.getDireccion().getNombre_direccion()).
                latitud(heladera.getDireccion().getPunto().getLatitud().toString()).
                longitud(heladera.getDireccion().getPunto().getLongitud().toString()).
                direccion(heladera.getDireccion().getDireccion()).
                cantidadViandas(heladera.getStock().toString()).
                capacidad(heladera.getCapacidad().toString()).
                estado(heladera.getEstado().toString());
         if(heladera.getVisitaTecnicas().size() > 0) {
             VisitaTecnica ultimaVisita = heladera.getVisitaTecnicas().get(heladera.getVisitaTecnicas().size() - 1);
             heladeraDTOBuilder.ultimaVisitaTecnica(ServiceVisitasTecnicas.toVisitaTecnicaDTO(ultimaVisita));
         }
          return heladeraDTOBuilder.build();
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

    public static Heladera crearHeladera(Context context) {
        Heladera heladera = new Heladera();
        heladera.setFechaYHoraInicio(LocalDateTime.now());
        heladera.setEstado(Estado.ACTIVA);
        heladera.setAperturas(new ArrayList<>());
        heladera.setSolicitudAperturas(new ArrayList<>());
        heladera.setVisitaTecnicas(new ArrayList<>());
        heladera.setDireccion(Direccion.builder().direccion(context.formParam("direccion"))
                .punto(Punto.builder().latitud(Double.valueOf(context.formParam("latitud"))).longitud(Double.valueOf(context.formParam("longitud"))).build())
                .nombre_direccion(context.formParam("nombre"))
                .build());
        heladera.setTopics(Arrays.asList(
                Topic.builder().condicionSuscripcionHeladera(new Desperfecto()).suscripciones(new ArrayList<>()).mensaje("Hubo un desperfecto en la heladera").build(),
                Topic.builder().condicionSuscripcionHeladera(new FaltanNViandas()).suscripciones(new ArrayList<>()).mensaje("La heladera está cerca de llenarse").build(),
                Topic.builder().condicionSuscripcionHeladera(new QuedanNViandas()).suscripciones(new ArrayList<>()).mensaje("La heladera está por quedarse vacía").build()
        ));
        heladera.setStock(0);
        heladera.setCapacidad(Integer.valueOf(context.formParam("capacidad")));
        heladera.setModeloHeladera(ServiceLocator.instanceOf(RepositorioModelos.class).buscar(Long.valueOf(context.formParam("modelo-id"))).get());
        heladera.setTiempoParaVisitarEnMinutos(180);
        return heladera;
    }
}