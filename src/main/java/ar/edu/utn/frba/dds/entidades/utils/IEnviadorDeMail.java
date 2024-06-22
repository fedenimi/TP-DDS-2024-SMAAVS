package ar.edu.utn.frba.dds.entidades.utils;

public interface IEnviadorDeMail {

    public void enviarMail(String mail, String asunto, String mensaje);
}
