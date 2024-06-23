package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.*;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.Punto;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.Topic;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Heladera {
    private ModeloHeladera modeloHeladera;
    private Integer id;
    private Punto punto;
    private String direccion;
    private Integer capacidad;
    @Setter private Estado estado;
    @Getter private LocalDate fechaInicio;
    private Integer tiempoParaVisitarEnMinutos;
    private List<Apertura> aperturas;
    private List<SolicitudApertura> solicitudAperturas;
    private List<VisitaTecnica> visitaTecnicas;
    private Integer stock;
    private List<Topic> topics;

    public void agregarSolicitudApertura(SolicitudApertura solicitudApertura) {
        solicitudAperturas.add(solicitudApertura);
    }

    public void validarApertura(Apertura apertura) {
        if(puedeAbrir(apertura)){
            aperturas.add(apertura);
        }
    }

    public boolean puedeAbrir(Apertura apertura) {
        return solicitudAperturas.stream().anyMatch(s ->
                s.getColaborador() == apertura.getColaborador() &&
                ChronoUnit.MINUTES.between(s.getFechaYHora(), apertura.getFechaYHora()) < tiempoParaVisitarEnMinutos
        );
    }

    public Float obtenerTemperaturaMinima() {
        return modeloHeladera.getTemperaturaMinima();
    }

    public Float obtenerTemperaturaMaxima() {
        return modeloHeladera.getTemperaturaMaxima();
    }
}
