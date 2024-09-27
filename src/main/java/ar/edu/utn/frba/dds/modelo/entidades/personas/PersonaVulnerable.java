package ar.edu.utn.frba.dds.modelo.entidades.personas;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Registro;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.Documento;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TarjetaPersonaVulnerable;
import ar.edu.utn.frba.dds.modelo.entidades.utils.converters.LocalDateConverter;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
@Entity
@Getter
@Table(name = "persona_vulnerable")
public class PersonaVulnerable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nombre", columnDefinition = "VARCHAR(255)")
    private String nombre;

    @Column(name = "fecha_de_nacimiento")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate fechaDeNacimiento;

    @Column(name = "fecha_de_registro")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate fechaDeRegistro;
    @Column(name = "domicilio", columnDefinition = "TEXT")
    private String domicilio;
    @Embedded
    private Documento documento;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "persona_vulnerable_id")
    private List<PersonaVulnerable> menoresACargo;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "tarjeta_persona_vulnerable_id", referencedColumnName = "codigo")
    private TarjetaPersonaVulnerable tarjeta;

    public int edad(LocalDate fecha) { return Period.between(this.fechaDeNacimiento, fecha).getYears(); }

    public int edad() {
        return this.edad(LocalDate.now());
    }

    public List<PersonaVulnerable> menoresACargoEn(LocalDate fecha) {
        return (List<PersonaVulnerable>) menoresACargo.stream().filter(menor -> menor.eraMenorEn(fecha));
    }

    public Integer cantidadDeMenoresACargoEn(LocalDate fecha){
        return menoresACargoEn(LocalDate.now()).size();
    }
    private boolean eraMenorEn(LocalDate fecha) {
        return fecha.isAfter(this.fechaDeNacimiento) && this.edad(fecha) < 18;
    }

    public Integer cantidadMaximaDeUsosDeTarjetaDiarios() {
        return tarjeta.getUsosMaximoBase() + tarjeta.getUsosMaximoPorMenor()*cantidadDeMenoresACargoEn(LocalDate.now());
    }

    public Long usosEn(LocalDate fecha) {
        return tarjeta.getUsos().stream().filter(u->u.getFechaDeUso().toLocalDate().equals(fecha)).count();
    }

    public Long usosDeHoy() {
        return usosEn(LocalDate.now());
    }
    public boolean puedeUsarLaTarjeta() {
        return this.usosDeHoy() < this.cantidadMaximaDeUsosDeTarjetaDiarios();
    }

}
