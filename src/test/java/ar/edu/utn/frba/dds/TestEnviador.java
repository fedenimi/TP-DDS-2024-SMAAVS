package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.modelo.entidades.enviadores.Enviador;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class TestEnviador {
    @Test
    public void enviarMsjTelegram(){
        Enviador enviador = mock(Enviador.class);
        doNothing().when(enviador).enviar(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
        enviador.enviar(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
        Mockito.verify(enviador).enviar(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
    }
}
