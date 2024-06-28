package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.*;
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
    @Getter private Integer id;
    private Punto punto;
    private String direccion;
    private Integer capacidad;
    @Setter @Getter private Estado estado;
    @Getter private LocalDate fechaInicio;
    private Integer tiempoParaVisitarEnMinutos;
    private List<Apertura> aperturas;
    private List<SolicitudApertura> solicitudAperturas;
    private List<VisitaTecnica> visitaTecnicas;
    @Getter private Integer stock;
    private List<Topic> topics;

    public Heladera(Integer id) {
        this.id = id;
    }

    public void agregarSolicitudApertura(SolicitudApertura solicitudApertura) {
        solicitudAperturas.add(solicitudApertura);
    }


    public Float obtenerTemperaturaMinima() {
        return modeloHeladera.getTemperaturaMinima();
    }

    public Float obtenerTemperaturaMaxima() {
        return modeloHeladera.getTemperaturaMaxima();
    }
}
