package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.*;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TarjetaColaborador;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.Direccion;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.Punto;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.Topic;
import ar.edu.utn.frba.dds.modelo.entidades.utils.converters.LocalDateTimeConverter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
@Entity
@Table(name = "heladera")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Heladera{
    @Id
    @GeneratedValue
    @Getter private Long id;

    @ManyToOne
    @JoinColumn(name = "modelo_heladera_id")
    private ModeloHeladera modeloHeladera;
    @Embedded
    private Direccion direccion;

    @Column(name = "cantidad_viandas", columnDefinition = "int")
    private Integer cantidadViandas;

    @Column(name = "capacidad", columnDefinition = "int")
    private Integer capacidad;

    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    @Setter @Getter private Estado estado;

    @Column(name = "fecha_y_hora_inicio")
    //@Convert(converter = LocalDateTimeConverter.class)
    @Getter private LocalDateTime fechaYHoraInicio;

    @Column(name = "tiempo_para_visitar_en_minutos", columnDefinition = "int")
    private Integer tiempoParaVisitarEnMinutos;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "heladera_id")
    private List<Apertura> aperturas;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "heladera_id")
    private List<SolicitudApertura> solicitudAperturas;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "heladera_id")
    private List<VisitaTecnica> visitaTecnicas;

    @Column(name = "stock", columnDefinition = "int")
    @Getter private Integer stock;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
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

    public Double obtenerTemperaturaMinima() {
        return modeloHeladera.getTemperaturaMinima();
    }

    public Double obtenerTemperaturaMaxima() {
        return modeloHeladera.getTemperaturaMaxima();
    }

    public boolean tieneFallas() {
        return estado != Estado.ACTIVA;
    }
}