package ar.edu.utn.frba.dds.modelo.entidades.personas;

import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.Documento;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TarjetaPersonaVulnerable;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class PersonaVulnerable {
    private String nombre;
    private LocalDate fechaDeNacimiento;
    private LocalDate fechaDeRegistro;
    private String domicilio;
    private Documento documento;
    private List<PersonaVulnerable> menoresACargo;
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
