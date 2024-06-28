package ar.edu.utn.frba.dds.modelo.entidades.datosPersonas;

import lombok.Getter;
@Getter
public class MedioDeContacto {
    private String valor;
    private TipoDeContacto tipo;

    public MedioDeContacto(String valor, TipoDeContacto tipo) {
        this.valor = valor;
        this.tipo = tipo;
    }
}
