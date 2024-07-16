package ar.edu.utn.frba.dds.modelo.entidades.enviadores;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface Enviador {
    public void enviar(String mailONumero, String tituloOAsunto, String mensaje);
}
