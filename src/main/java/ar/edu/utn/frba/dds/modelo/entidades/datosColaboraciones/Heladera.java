package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.*;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TarjetaColaborador;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.Punto;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.Topic;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
@Entity
@Table(name = "heladera")
@NoArgsConstructor
public class Heladera {
    @Id
    @GeneratedValue
    @Getter private Long id;

    @ManyToOne
    @JoinColumn(name = "modelo_heladera_id")
    private ModeloHeladera modeloHeladera;

    @Column(name = "punto")
    @OneToOne
    private Punto punto;

    @Column(name = "direccion", columnDefinition = "TEXT")
    private String direccion;

    @Column(name = "capacidad", columnDefinition = "int")
    private Integer capacidad;

    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    @Setter @Getter private Estado estado;

    //TODO: converter
    @Getter private LocalDateTime fechaYHoraInicio;

    @Column(name = "tiempo_para_visitar_en_minutos", columnDefinition = "int")
    private Integer tiempoParaVisitarEnMinutos;

    @OneToMany
    @JoinColumn(name = "heladera_id")
    private List<Apertura> aperturas;

    @OneToMany
    @JoinColumn(name = "heladera_id")
    private List<SolicitudApertura> solicitudAperturas;

    @OneToMany
    @JoinColumn(name = "heladera_id")
    private List<VisitaTecnica> visitaTecnicas;

    @Column(name = "stock", columnDefinition = "int")
    @Getter private Integer stock;

    @OneToMany
    @JoinColumn(name="heladera_id")
    @Getter private List<Topic> topics;

    public Heladera(Long id, LocalDateTime fechaYHoraInicio) {
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
