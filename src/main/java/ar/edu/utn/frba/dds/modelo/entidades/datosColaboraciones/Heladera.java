package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.*;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TarjetaColaborador;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.Punto;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.Topic;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Heladera {
    private ModeloHeladera modeloHeladera;
    @Getter private String id;
    private Punto punto;
    private String direccion;
    private Integer capacidad;
    @Setter @Getter private Estado estado;
    @Getter private LocalDateTime fechaYHoraInicio;
    private Integer tiempoParaVisitarEnMinutos;
    private List<Apertura> aperturas;
    private List<SolicitudApertura> solicitudAperturas;
    private List<VisitaTecnica> visitaTecnicas;
    @Getter private Integer stock;
    @Getter private List<Topic> topics;

    public Heladera(String id, LocalDateTime fechaYHoraInicio) {
        this.id = id;
        this.fechaYHoraInicio = fechaYHoraInicio;
    }

    public void agregarSolicitudApertura(SolicitudApertura solicitudApertura) {
        solicitudAperturas.add(solicitudApertura);
    }

    public void agregarApertura(Apertura apertura) {
        aperturas.add(apertura);
    }

    public SolicitudApertura buscarSolicitudAperturaPor(TarjetaColaborador tarjetaColaborador, LocalDateTime fechaYHora) {
        return solicitudAperturas.stream()
                .filter(solicitudApertura -> solicitudApertura.getTarjetaColaborador().equals(tarjetaColaborador) &&
                solicitudApertura.getFechaYHora().equals(fechaYHora)).findFirst().orElse(null);
    }

    public Float obtenerTemperaturaMinima() {
        return modeloHeladera.getTemperaturaMinima();
    }

    public Float obtenerTemperaturaMaxima() {
        return modeloHeladera.getTemperaturaMaxima();
    }
}
