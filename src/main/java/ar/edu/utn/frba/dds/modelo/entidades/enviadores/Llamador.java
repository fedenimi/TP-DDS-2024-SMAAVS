package ar.edu.utn.frba.dds.modelo.entidades.enviadores;

import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.MedioDeContacto;
import lombok.Setter;

import java.util.List;

public class Llamador {
    private Enviador enviadorDeMail;
    @Setter private Enviador enviadorDeTelegram;
    private Enviador enviadorDeWhatsapp;

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
