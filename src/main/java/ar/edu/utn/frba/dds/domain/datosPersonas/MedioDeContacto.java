package ar.edu.utn.frba.dds.domain.datosPersonas;

public class MedioDeContacto {
    private String valor;
    private TipoDeContacto tipo;

    public MedioDeContacto(String valor, TipoDeContacto tipo) {
        this.valor = valor;
        this.tipo = tipo;
    }
}
