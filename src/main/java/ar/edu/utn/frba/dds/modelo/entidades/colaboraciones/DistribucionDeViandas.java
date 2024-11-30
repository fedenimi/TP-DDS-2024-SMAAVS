package ar.edu.utn.frba.dds.modelo.entidades.colaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.ColaboracionesProperties;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Motivo;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Apertura;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.SolicitudApertura;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.utils.converters.LocalDateConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("distribucion_viandas")
public class DistribucionDeViandas extends Puntuable {
    @ManyToOne
    @JoinColumn(name = "heladera_origen_id")
    private Heladera heladeraOrigen;

    @ManyToOne
    @JoinColumn(name = "heladera_destino_id")
    private Heladera heladeraDestino;

    @Column(name = "cantidad_de_viandas", columnDefinition = "int")
    private Integer cantidadDeViandas;

    @Column(name = "motivo_distribucion")
    @Enumerated(EnumType.STRING)
    private Motivo motivoDistribucion;

    @Column(name = "fecha")
    private LocalDateTime fecha;
    // TODO: cambiar a localdatetime

    @OneToOne()
    @JoinColumn(name = "solicitud_apertura_origen_id")
    private SolicitudApertura solicitudAperturaOrigen;

    @OneToOne()
    @JoinColumn(name = "apertura_origen_id")
    private Apertura aperturaOrigen;

    @OneToOne()
    @JoinColumn(name = "solicitud_apertura_destino_id")
    private SolicitudApertura solicitudAperturaDestino;

    @OneToOne()
    @JoinColumn(name = "apertura_destino_id")
    private Apertura aperturaDestino;



    public DistribucionDeViandas(Integer cantidadDeViandas, Colaborador colaborador, Heladera heladeraDestino, Heladera heladeraOrigen, LocalDateTime fecha) {
        this.cantidadDeViandas = cantidadDeViandas;
        this.heladeraDestino = heladeraDestino;
        this.fecha = fecha;
        this.heladeraOrigen = heladeraOrigen;
    }

    public DistribucionDeViandas(Integer cantidadDeViandas, Colaborador colaborador) {
        this.cantidadDeViandas = cantidadDeViandas;
        this.colaborador = colaborador;
    }

    @Override
    public Double puntaje() {
        if(aperturaDestino == null) {
            return 0D;
        }
        return cantidadDeViandas.doubleValue();
    }

    @Override
    public int cantidadDeMesesSiendoHeladera() {
        return 0;
    }

    @Override
    public Double getMultiplicador() {
        return Double.parseDouble(ColaboracionesProperties.getInstance().propertyFromName("distribucion_viandas"));
    }
}
