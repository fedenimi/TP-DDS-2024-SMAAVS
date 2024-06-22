package ar.edu.utn.frba.dds.entidades.datosPersonas;

import lombok.Getter;

public class MedioDeContacto {
    @Getter private String valor;
    private TipoDeContacto tipo;

    public MedioDeContacto(String valor, TipoDeContacto tipo) {
        this.valor = valor;
        this.tipo = tipo;
    }
}
