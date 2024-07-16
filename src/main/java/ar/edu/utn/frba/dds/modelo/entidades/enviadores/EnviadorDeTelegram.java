package ar.edu.utn.frba.dds.modelo.entidades.enviadores;

import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
public class EnviadorDeTelegram implements Enviador{
  private AdapterEnviadorTelegram adapterEnviadorTelegram;
  @Override
    public void enviar(String numero, String titulo, String mensaje){
      adapterEnviadorTelegram.enviar(numero, titulo, mensaje);
    }
}
