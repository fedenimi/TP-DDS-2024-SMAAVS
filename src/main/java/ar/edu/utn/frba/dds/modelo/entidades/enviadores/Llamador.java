package ar.edu.utn.frba.dds.modelo.entidades.enviadores;

import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.MedioDeContacto;

import java.util.List;

public class Llamador {
    private EnviadorDeMail enviadorDeMail;
    private EnviadorDeTelegram enviadorDeTelegram;
    private EnviadorDeWhatsapp enviadorDeWhatsapp;
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
