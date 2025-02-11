package ar.edu.utn.frba.dds.modelo.entidades.utils;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.modelo.cronJobs.MainPuntos;
import ar.edu.utn.frba.dds.modelo.entidades.acceso.Permiso;
import ar.edu.utn.frba.dds.modelo.entidades.acceso.Usuario;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.OfrecerProducto;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Oferta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Rubro;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.FalloHeladeraTecnico;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores.ReceptorSensorTemperatura;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.ModeloHeladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.*;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.Direccion;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.Punto;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Tecnico;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.AlertaSuscripcion;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.SuscripcionHumana;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.TipoNotificacion;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.Topic;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.condiciones.Desperfecto;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.condiciones.FaltanNViandas;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.condiciones.QuedanNViandas;
import ar.edu.utn.frba.dds.modelo.repositorios.*;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.print.Doc;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Initializer{
    public static void init() {
        ModeloHeladera modelo1 = ModeloHeladera.builder().nombre("Heladera Samsung").temperaturaMinima(0.0).temperaturaMaxima(10.0).build();
        ModeloHeladera modelo2 = ModeloHeladera.builder().nombre("Heladera LG").temperaturaMinima(0.0).temperaturaMaxima(10.0).build();
        ModeloHeladera modelo3 = ModeloHeladera.builder().nombre("Heladera Whirlpool").temperaturaMinima(0.0).temperaturaMaxima(10.0).build();


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
                .modeloHeladera(modelo1)
                .topics(Arrays.asList(Topic.builder().condicionSuscripcionHeladera(new FaltanNViandas()).suscripciones(new ArrayList<>()).mensaje("Faltan pocas viandas para que la heladera se llene").build(),
                        Topic.builder().condicionSuscripcionHeladera(new QuedanNViandas()).suscripciones(new ArrayList<>()).mensaje("Quedan pocas viandas en la heladera").build(),
                        Topic.builder().condicionSuscripcionHeladera(new Desperfecto()).suscripciones(new ArrayList<>()).mensaje("La heladera sufrió un desperfecto").build())
                )
                .build();

        ReceptorSensorTemperatura receptorSensorTemperatura1 = ReceptorSensorTemperatura.builder().heladera(heladera1).mediciones(new ArrayList<>()).build();

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
                .modeloHeladera(modelo2)
                .topics(Arrays.asList(Topic.builder().condicionSuscripcionHeladera(new FaltanNViandas()).suscripciones(new ArrayList<>()).mensaje("Faltan pocas viandas para que la heladera se llene").build(),
                        Topic.builder().condicionSuscripcionHeladera(new QuedanNViandas()).suscripciones(new ArrayList<>()).mensaje("Quedan pocas viandas en la heladera").build(),
                        Topic.builder().condicionSuscripcionHeladera(new Desperfecto()).suscripciones(new ArrayList<>()).mensaje("La heladera sufrió un desperfecto").build())
                )
                .build();

        ReceptorSensorTemperatura receptorSensorTemperatura2 = ReceptorSensorTemperatura.builder().heladera(heladera2).mediciones(new ArrayList<>()).build();

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
                .modeloHeladera(modelo3)
                .topics(Arrays.asList(Topic.builder().condicionSuscripcionHeladera(new FaltanNViandas()).suscripciones(new ArrayList<>()).mensaje("Faltan pocas viandas para que la heladera se llene").build(),
                        Topic.builder().condicionSuscripcionHeladera(new QuedanNViandas()).suscripciones(new ArrayList<>()).mensaje("Quedan pocas viandas en la heladera").build(),
                        Topic.builder().condicionSuscripcionHeladera(new Desperfecto()).suscripciones(new ArrayList<>()).mensaje("La heladera sufrió un desperfecto").build())
                )
                .build();

        ReceptorSensorTemperatura receptorSensorTemperatura3 = ReceptorSensorTemperatura.builder().heladera(heladera3).mediciones(new ArrayList<>()).build();

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
                tarjetaColaborador(TarjetaColaborador.builder().fechaEmision(LocalDateTime.now()).codigoAlfanumerico("1234567890").build()).
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
                Colaborador.builder().tipoDeColaborador(TipoDeColaborador.JURIDICA).puntosCanjeados(0D).puntosDisponibles(0D).build()
        ).build();

        Tecnico tecnico1 = Tecnico.builder().nombre("Tecnico1").apellido("Tecnico1").documento(Documento.builder().
                numero("12345678").
                tipo(TipoDocumento.DNI).build()).
                cuil("12345678").
                medioDeContacto(MedioDeContacto.builder().valor("podolskytomi@gmail.com").tipo(TipoDeContacto.MAIL).build()).
                ultimoPunto(Punto.builder().latitud(-34.632166).longitud(-58.425981).build()).
                falloHeladera(new ArrayList<>(
                        List.of(
                                FalloHeladeraTecnico.builder().fechaYHora(LocalDateTime.now()).visitaRealizada(false).heladera(heladera1).build(),
                                FalloHeladeraTecnico.builder().fechaYHora(LocalDateTime.now()).visitaRealizada(false).heladera(heladera2).build()
                        )
                )).
                build();


        Usuario tecnicoUsuario = Usuario.builder().nombre("tecnico").contrasenia("tecnico").permisos(List.of(Permiso.TECNICO)).tecnicoAsociado(tecnico1).build();

        RepositorioHeladeras repositorioDeHeladeras = ServiceLocator.instanceOf(RepositorioHeladeras.class);
        repositorioDeHeladeras.guardar(heladera1);
        repositorioDeHeladeras.guardar(heladera2);
        repositorioDeHeladeras.guardar(heladera3);

        RepositorioReceptoresTemperatura repositorioDeReceptoresTemperatura = ServiceLocator.instanceOf(RepositorioReceptoresTemperatura.class);
        repositorioDeReceptoresTemperatura.guardar(receptorSensorTemperatura1);
        repositorioDeReceptoresTemperatura.guardar(receptorSensorTemperatura2);
        repositorioDeReceptoresTemperatura.guardar(receptorSensorTemperatura3);

        RepositorioColaboradores repositorioDeColaboradores = ServiceLocator.instanceOf(RepositorioColaboradores.class);
        repositorioDeColaboradores.guardar(colaborador1);
        repositorioDeColaboradores.guardar(colaborador2);

        RepositorioOfrecerProductos repositorioDeOfrecerProductos = ServiceLocator.instanceOf(RepositorioOfrecerProductos.class);
        repositorioDeOfrecerProductos.guardar(ofrecerProducto1);
        repositorioDeOfrecerProductos.guardar(ofrecerProducto2);
        repositorioDeOfrecerProductos.guardar(ofrecerProducto3);

        RepositorioAlertas repositorioAlertas = ServiceLocator.instanceOf(RepositorioAlertas.class);
        repositorioAlertas.guardar(alerta1);
        repositorioAlertas.guardar(alerta2);

        RepositorioAlertaSuscripciones repositorioAlertaSuscripciones = ServiceLocator.instanceOf(RepositorioAlertaSuscripciones.class);
        repositorioAlertaSuscripciones.guardar(alertaSuscripcion1);
        repositorioAlertaSuscripciones.guardar(alertaSuscripcion2);
        repositorioAlertaSuscripciones.guardar(alertaSuscripcion3);

        RepositorioUsuarios repositorioUsuarios = ServiceLocator.instanceOf(RepositorioUsuarios.class);
        repositorioUsuarios.guardar(admin);
        repositorioUsuarios.guardar(tecnicoUsuario);

        RepositorioTecnicos repositorioTecnicos = ServiceLocator.instanceOf(RepositorioTecnicos.class);
        repositorioTecnicos.guardar(tecnico1);

    }
}
