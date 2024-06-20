package ar.edu.utn.frba.dds.domain.utils;

public interface IEnviadorDeMail {

    public void enviarMail(String mail, String asunto, String mensaje);
}
