package ar.edu.utn.frba.dds.modelo.entidades.utils;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.ModeloHeladera;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;

public class Initializer {
    public static void init() {
        Heladera heladera1 = Heladera
                .builder()
                .capacidad(500)
                .modeloHeladera(ModeloHeladera.builder().nombre("Heladera Samsung").temperaturaMinima(0.0).temperaturaMaxima(10.0).build())
                .build();

        Heladera heladera2 = Heladera
                .builder()
                .capacidad(600)
                .modeloHeladera(ModeloHeladera.builder().nombre("Heladera LG").temperaturaMinima(0.0).temperaturaMaxima(10.0).build())
                .build();

        Heladera heladera3 = Heladera
                .builder()
                .capacidad(700)
                .modeloHeladera(ModeloHeladera.builder().nombre("Heladera Whirlpool").temperaturaMinima(0.0).temperaturaMaxima(10.0).build())
                .build();

        RepositorioHeladeras repositorioDeProductos = ServiceLocator.instanceOf(RepositorioHeladeras.class);

        repositorioDeProductos.guardar(heladera1);
        repositorioDeProductos.guardar(heladera2);
        repositorioDeProductos.guardar(heladera3);
    }
}
