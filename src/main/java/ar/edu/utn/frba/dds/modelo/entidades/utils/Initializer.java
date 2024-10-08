package ar.edu.utn.frba.dds.modelo.entidades.utils;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.modelo.entidades.acceso.Permiso;
import ar.edu.utn.frba.dds.modelo.entidades.acceso.Usuario;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.OfrecerProducto;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Oferta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Rubro;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.ModeloHeladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.Documento;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.FormaColaboracion;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TipoDeColaborador;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TipoDocumento;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.Direccion;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.Punto;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.AlertaSuscripcion;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.SuscripcionHumana;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.TipoNotificacion;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.Topic;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.condiciones.Desperfecto;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.condiciones.FaltanNViandas;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.condiciones.QuedanNViandas;
import ar.edu.utn.frba.dds.modelo.repositorios.*;

import javax.print.Doc;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
                .topics(Arrays.asList(Topic.builder().condicionSuscripcionHeladera(new FaltanNViandas()).suscripciones(new ArrayList<>()).mensaje("Faltan pocas viandas para que la heladera se llene").build(),
                        Topic.builder().condicionSuscripcionHeladera(new QuedanNViandas()).suscripciones(new ArrayList<>()).mensaje("Quedan pocas viandas en la heladera").build(),
                        Topic.builder().condicionSuscripcionHeladera(new Desperfecto()).suscripciones(new ArrayList<>()).mensaje("La heladera sufrió un desperfecto").build())
                )
                .build();

        Heladera heladera2 = Heladera
                .builder()
                .capacidad(600)
                .stock(20)
                .estado(Estado.FALLA_CONEXION)
                .fechaYHoraInicio(LocalDateTime.now())
                .tiempoParaVisitarEnMinutos(30)
                .aperturas(null)
                .solicitudAperturas(null)
                .visitaTecnicas(null)
                .direccion(Direccion.builder().nombre_direccion("Av. Rivadavia 1234").punto(Punto.builder().latitud(-34.6037).longitud(-58.3816).build()).direccion("Av. Rivadavia 1234").build())
                .modeloHeladera(ModeloHeladera.builder().nombre("Heladera LG").temperaturaMinima(0.0).temperaturaMaxima(10.0).build())
                .topics(Arrays.asList(Topic.builder().condicionSuscripcionHeladera(new FaltanNViandas()).suscripciones(new ArrayList<>()).mensaje("Faltan pocas viandas para que la heladera se llene").build(),
                        Topic.builder().condicionSuscripcionHeladera(new QuedanNViandas()).suscripciones(new ArrayList<>()).mensaje("Quedan pocas viandas en la heladera").build(),
                        Topic.builder().condicionSuscripcionHeladera(new Desperfecto()).suscripciones(new ArrayList<>()).mensaje("La heladera sufrió un desperfecto").build())
                )
                .build();

        Heladera heladera3 = Heladera
                .builder()
                .capacidad(700)
                .stock(30)
                .estado(Estado.FRAUDE)
                .fechaYHoraInicio(LocalDateTime.now())
                .tiempoParaVisitarEnMinutos(30)
                .aperturas(null)
                .solicitudAperturas(null)
                .visitaTecnicas(null)
                .direccion(Direccion.builder().nombre_direccion("Av. Belgrano 1234").punto(Punto.builder().latitud(-35.1234).longitud(-58.3016).build()).direccion("Av. Belgrano 1234").build())
                .modeloHeladera(ModeloHeladera.builder().nombre("Heladera Whirlpool").temperaturaMinima(0.0).temperaturaMaxima(10.0).build())
                .topics(Arrays.asList(Topic.builder().condicionSuscripcionHeladera(new FaltanNViandas()).suscripciones(new ArrayList<>()).mensaje("Faltan pocas viandas para que la heladera se llene").build(),
                        Topic.builder().condicionSuscripcionHeladera(new QuedanNViandas()).suscripciones(new ArrayList<>()).mensaje("Quedan pocas viandas en la heladera").build(),
                        Topic.builder().condicionSuscripcionHeladera(new Desperfecto()).suscripciones(new ArrayList<>()).mensaje("La heladera sufrió un desperfecto").build())
                )
                .build();

        SuscripcionHumana suscripcionHumana1 = SuscripcionHumana.builder().cantidad(5).heladera(heladera1).tipoNotificacion(TipoNotificacion.FALTAN_N_VIANDAS).build();
        SuscripcionHumana suscripcionHumana2 = SuscripcionHumana.builder().cantidad(3).heladera(heladera2).tipoNotificacion(TipoNotificacion.QUEDAN_N_VIANDAS).build();
        SuscripcionHumana suscripcionHumana3 = SuscripcionHumana.builder().heladera(heladera3).tipoNotificacion(TipoNotificacion.DESPERFECTO).build();
        AlertaSuscripcion alertaSuscripcion1 = AlertaSuscripcion.builder().suscripcionHumana(suscripcionHumana1).descripcion_alerta("Faltan 5 viandas").build();
        AlertaSuscripcion alertaSuscripcion2 = AlertaSuscripcion.builder().suscripcionHumana(suscripcionHumana2).descripcion_alerta("Quedan 3 viandas").build();
        AlertaSuscripcion alertaSuscripcion3 = AlertaSuscripcion.builder().suscripcionHumana(suscripcionHumana3).descripcion_alerta("Sufrió un desperfecto").build();

        Colaborador colaborador1 = Colaborador.builder().
                nombre("Juan").
                apellido("Perez").
                puntosCanjeados(0.0).
                puntosDisponibles(0.0).
                documento(Documento.builder().numero("12345678").tipo(TipoDocumento.DNI).build()).
                tipoDeColaborador(TipoDeColaborador.HUMANA).
                formasDeColaborar(Arrays.asList(FormaColaboracion.DISTRIBUCION_VIANDAS, FormaColaboracion.DONACION_VIANDAS)).
                suscripciones(Arrays.asList(suscripcionHumana1, suscripcionHumana2, suscripcionHumana3)).
                alertaSuscripciones(Arrays.asList(alertaSuscripcion1, alertaSuscripcion2, alertaSuscripcion3)).
                build();

        Rubro rubro1 = Rubro.builder().nombre("Gastronomia").build();
        Rubro rubro2 = Rubro.builder().nombre("Bazar").build();

        Colaborador colaborador2 = Colaborador.builder().
                puntosCanjeados(0.0).
                puntosDisponibles(0.0).
                tipoDeColaborador(TipoDeColaborador.JURIDICA).
                formasDeColaborar(List.of(FormaColaboracion.OFRECER_PRODUCTOS)).
                documento(Documento.builder().numero("123456789").tipo(TipoDocumento.DNI).build()).
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

        Alerta alerta1 = Alerta.builder().tipoAlerta(Estado.FRAUDE).fechaYHora(LocalDateTime.now()).heladera(heladera3).build();
        Alerta alerta2 = Alerta.builder().tipoAlerta(Estado.FALLA_CONEXION).fechaYHora(LocalDateTime.now()).heladera(heladera2).build();

        Usuario admin = Usuario.builder().nombre("admin").contrasenia("admin").permisos(List.of(Permiso.ADMIN)).colaboradorAsociado(
                Colaborador.builder().tipoDeColaborador(TipoDeColaborador.JURIDICA).build()
        ).build();

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

        RepositorioAlertas repositorioAlertas = ServiceLocator.instanceOf(RepositorioAlertas.class);
        repositorioAlertas.beginTransaction();
        repositorioAlertas.guardar(alerta1);
        repositorioAlertas.guardar(alerta2);
        repositorioAlertas.commitTransaction();

        RepositorioAlertaSuscripciones repositorioAlertaSuscripciones = ServiceLocator.instanceOf(RepositorioAlertaSuscripciones.class);
        repositorioAlertaSuscripciones.beginTransaction();
        repositorioAlertaSuscripciones.guardar(alertaSuscripcion1);
        repositorioAlertaSuscripciones.guardar(alertaSuscripcion2);
        repositorioAlertaSuscripciones.guardar(alertaSuscripcion3);
        repositorioAlertaSuscripciones.commitTransaction();

        RepositorioUsuarios repositorioUsuarios = ServiceLocator.instanceOf(RepositorioUsuarios.class);
        repositorioUsuarios.beginTransaction();
        repositorioUsuarios.guardar(admin);
        repositorioUsuarios.commitTransaction();

    }
}
