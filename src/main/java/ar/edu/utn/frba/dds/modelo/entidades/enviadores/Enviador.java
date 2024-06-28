package ar.edu.utn.frba.dds.modelo.entidades.enviadores;

public interface Enviador {
    public void enviar(String mailONumero, String tituloOAsunto, String mensaje);
}
