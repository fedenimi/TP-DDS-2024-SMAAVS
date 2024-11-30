package ar.edu.utn.frba.dds.modelo.entidades.colaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.ColaboracionesProperties;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Vianda;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Apertura;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.SolicitudApertura;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.utils.converters.LocalDateConverter;
import ar.edu.utn.frba.dds.modelo.entidades.utils.converters.LocalDateTimeConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("donacion_viandas")
public class DonacionDeViandas extends Puntuable{
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "vianda_id")
    private List<Vianda> viandasDonadas;

    @Column(name = "fecha")
    private LocalDateTime fecha;
    // TODO: cambiar a localdatetime
    @ManyToOne
    @JoinColumn(name = "heladera_id")
    @Getter private Heladera heladera;

    @OneToOne()
    @JoinColumn(name = "solicitud_apertura_id")
    private SolicitudApertura solicitudApertura;

    @OneToOne()
    @JoinColumn(name = "apertura_id")
    private Apertura apertura;
    public DonacionDeViandas(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public DonacionDeViandas(Colaborador colaborador, List<Vianda> viandas, LocalDateTime fecha) {
        this.colaborador = colaborador;
        this.viandasDonadas = viandas;
        this.fecha = fecha;
    }

    @Override
    public Double puntaje() {
        if(apertura == null) {
            return 0D;
        }
        Integer ret = viandasDonadas.size();
        return ret.doubleValue();
    }

    @Override
    public int cantidadDeMesesSiendoHeladera() {
        return 0;
    }


    public Integer cantidadDeViandas() {
        return viandasDonadas.size();
    }

    @Override
    public Double getMultiplicador() {
        return Double.parseDouble(ColaboracionesProperties.getInstance().propertyFromName("donacion_viandas"));
    }
}
