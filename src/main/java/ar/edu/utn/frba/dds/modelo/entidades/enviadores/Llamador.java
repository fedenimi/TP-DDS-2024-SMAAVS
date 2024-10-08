package ar.edu.utn.frba.dds.modelo.entidades.enviadores;

import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.MedioDeContacto;
import lombok.Setter;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class Llamador {
    @Setter private Enviador enviadorDeMail = new EnviadorDeMail();
    @Setter private Enviador enviadorDeTelegram = new EnviadorDeTelegram(new EnviadorTelegramAPI("botToken"));
    private Enviador enviadorDeWhatsapp = new EnviadorDeWhatsapp();

    private static Llamador instance = null;
    public static Llamador getInstance() {
        if(instance == null)
            instance = new Llamador();
        return instance;
    }
    public void llamar(List<MedioDeContacto> mediosDeContacto, String mensaje, String asuntoOTitulo) {
        mediosDeContacto.forEach(medioDeContacto->{
            switch(medioDeContacto.getTipo()){
                case MAIL:
                    enviadorDeMail.enviar(medioDeContacto.getValor(), asuntoOTitulo, mensaje);
                    break;
                case WHATSAPP:
                    enviadorDeWhatsapp.enviar(medioDeContacto.getValor(), asuntoOTitulo, mensaje);
                    break;
                case TELEGRAM:
                    enviadorDeTelegram.enviar(medioDeContacto.getValor(), asuntoOTitulo, mensaje);
                    break;
            }
        });
    }
}
