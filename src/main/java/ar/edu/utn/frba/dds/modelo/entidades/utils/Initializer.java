package ar.edu.utn.frba.dds.modelo.entidades.utils;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.ModeloHeladera;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.Direccion;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.Punto;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;

import java.time.LocalDateTime;

public class Initializer {
    public static void init() {
        Heladera heladera1 = Heladera
                .builder()
                .capacidad(500)
                .stock(10)
                .estado(Estado.ACTIVA)
                .direccion(Direccion.builder().nombre_direccion("Av. Corrientes 1234").punto(Punto.builder().latitud(-34.632166).longitud(-58.425981).build()).direccion("Av. Corrientes 1234").build())
                .fechaYHoraInicio(LocalDateTime.now())
                .tiempoParaVisitarEnMinutos(30)
                .aperturas(null)
                .solicitudAperturas(null)
                .visitaTecnicas(null)
                .modeloHeladera(ModeloHeladera.builder().nombre("Heladera Samsung").temperaturaMinima(0.0).temperaturaMaxima(10.0).build())
                .build();

        Heladera heladera2 = Heladera
                .builder()
                .capacidad(600)
                .stock(20)
                .estado(Estado.ACTIVA)
                .fechaYHoraInicio(LocalDateTime.now())
                .tiempoParaVisitarEnMinutos(30)
                .aperturas(null)
                .solicitudAperturas(null)
                .visitaTecnicas(null)
                .direccion(Direccion.builder().nombre_direccion("Av. Rivadavia 1234").punto(Punto.builder().latitud(-34.6037).longitud(-58.3816).build()).direccion("Av. Rivadavia 1234").build())
                .modeloHeladera(ModeloHeladera.builder().nombre("Heladera LG").temperaturaMinima(0.0).temperaturaMaxima(10.0).build())
                .build();

        Heladera heladera3 = Heladera
                .builder()
                .capacidad(700)
                .stock(30)
                .estado(Estado.ACTIVA)
                .fechaYHoraInicio(LocalDateTime.now())
                .tiempoParaVisitarEnMinutos(30)
                .aperturas(null)
                .solicitudAperturas(null)
                .visitaTecnicas(null)
                .direccion(Direccion.builder().nombre_direccion("Av. Belgrano 1234").punto(Punto.builder().latitud(-34.6037).longitud(-58.3816).build()).direccion("Av. Belgrano 1234").build())
                .modeloHeladera(ModeloHeladera.builder().nombre("Heladera Whirlpool").temperaturaMinima(0.0).temperaturaMaxima(10.0).build())
                .build();

        RepositorioHeladeras repositorioDeHeladeras = ServiceLocator.instanceOf(RepositorioHeladeras.class);

        repositorioDeHeladeras.beginTransaction();
        repositorioDeHeladeras.guardar(heladera1);
        repositorioDeHeladeras.guardar(heladera2);
        repositorioDeHeladeras.guardar(heladera3);
        repositorioDeHeladeras.commitTransaction();


    }
}
