package ar.edu.utn.frba.dds.modelo.entidades.enviadores;

import ar.edu.utn.frba.dds.modelo.entidades.localizacion.RecomendadorDePuntos;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

public class EnviadorDeMail implements Enviador {
    public void enviar(String mail, String asunto, String mensaje) {
        CompletableFuture.runAsync(() -> {
            final String userName = "smaavstp@gmail.com";
            final String password = "kphw tqrh dfpm owir";

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {

                    return new PasswordAuthentication(userName, password);

                }
            });
            try {
                MimeMessage mimeMessage = new MimeMessage(session);
                mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mail, true));
                mimeMessage.setSubject(asunto);
                mimeMessage.setText(mensaje);
                System.out.println("enviando...");
                Transport.send(mimeMessage);
                System.out.println("el mensaje fue enviado exitosamente...");

            } catch (MessagingException me) {
                System.out.println("Exception: " + me);

            }
        });
    }
}
