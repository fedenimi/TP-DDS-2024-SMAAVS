package ar.edu.utn.frba.dds.modelo.entidades.personas;

import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.Documento;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TarjetaPersonaVulnerable;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
@Entity
@Table(name = "persona_vulnerable")
public class PersonaVulnerable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "nombre", columnDefinition = "VARCHAR")
    private String nombre;
    //TODO converters
    private LocalDate fechaDeNacimiento;
    private LocalDate fechaDeRegistro;
    @Column(name = "domicilio", columnDefinition = "TEXT")
    private String domicilio;
    @OneToOne
    //TODO completar
    private Documento documento;
    @OneToMany
    @JoinColumn(name = "persona_vulnerable_id")
    private List<PersonaVulnerable> menoresACargo;
    @OneToOne
    //TODO completar
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
