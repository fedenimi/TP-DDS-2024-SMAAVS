package ar.edu.utn.frba.dds.domain.personas;

import ar.edu.utn.frba.dds.domain.datosPersonas.Documento;
import ar.edu.utn.frba.dds.domain.datosPersonas.Tarjeta;

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
    private Tarjeta tarjeta;

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

}
