package ar.edu.utn.frba.dds.modelo.entidades.utils;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.OfrecerProducto;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Oferta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Rubro;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.ModeloHeladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.Documento;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.FormaColaboracion;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TipoDeColaborador;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TipoDocumento;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.Direccion;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.Punto;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioOfrecerProductos;

import javax.print.Doc;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

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

        Colaborador colaborador1 = Colaborador.builder().
                nombre("Juan").
                apellido("Perez").
                puntosCanjeados(0.0).
                puntosDisponibles(0.0).
                documento(Documento.builder().numero("12345678").tipo(TipoDocumento.DNI).build()).
                tipoDeColaborador(TipoDeColaborador.HUMANA).
                formasDeColaborar(Arrays.asList(FormaColaboracion.DISTRIBUCION_VIANDAS, FormaColaboracion.DONACION_VIANDAS)).
                build();

        Rubro rubro1 = Rubro.builder().nombre("Gastronomia").build();
        Rubro rubro2 = Rubro.builder().nombre("Bazar").build();

        Colaborador colaborador2 = Colaborador.builder().
                puntosCanjeados(0.0).
                puntosDisponibles(0.0).
                tipoDeColaborador(TipoDeColaborador.JURIDICA).
                formasDeColaborar(List.of(FormaColaboracion.OFRECER_PRODUCTOS)).
                build();

        OfrecerProducto ofrecerProducto1 = OfrecerProducto.builder().
                colaborador(colaborador2).
                oferta(Oferta.builder().puntajeMinimo(100D).nombre("Kilo de helado Freddo").rubro(rubro1).build()).
                build();

        OfrecerProducto ofrecerProducto2 = OfrecerProducto.builder().
                colaborador(colaborador2).
                oferta(Oferta.builder().puntajeMinimo(200D).nombre("Kilo de helado Persicco").rubro(rubro1).build()).
                build();

        OfrecerProducto ofrecerProducto3 = OfrecerProducto.builder().
                colaborador(colaborador2).
                oferta(Oferta.builder().puntajeMinimo(300D).nombre("Pecera").rubro(rubro2).build()).
                build();


        RepositorioHeladeras repositorioDeHeladeras = ServiceLocator.instanceOf(RepositorioHeladeras.class);

        repositorioDeHeladeras.beginTransaction();
        repositorioDeHeladeras.guardar(heladera1);
        repositorioDeHeladeras.guardar(heladera2);
        repositorioDeHeladeras.guardar(heladera3);
        repositorioDeHeladeras.commitTransaction();

        RepositorioColaboradores repositorioDeColaboradores = ServiceLocator.instanceOf(RepositorioColaboradores.class);

        repositorioDeColaboradores.beginTransaction();
        repositorioDeColaboradores.guardar(colaborador1);
        repositorioDeColaboradores.guardar(colaborador2);
        repositorioDeColaboradores.commitTransaction();

        RepositorioOfrecerProductos repositorioDeOfrecerProductos = ServiceLocator.instanceOf(RepositorioOfrecerProductos.class);

        repositorioDeOfrecerProductos.beginTransaction();
        repositorioDeOfrecerProductos.guardar(ofrecerProducto1);
        repositorioDeOfrecerProductos.guardar(ofrecerProducto2);
        repositorioDeOfrecerProductos.guardar(ofrecerProducto3);
        repositorioDeOfrecerProductos.commitTransaction();


    }
}
