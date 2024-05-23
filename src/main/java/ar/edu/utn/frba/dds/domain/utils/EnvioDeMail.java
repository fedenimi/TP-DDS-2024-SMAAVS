package ar.edu.utn.frba.dds.domain.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EnvioDeMail {
    public static void enviarMail(String mail, String nombre, String contrasenia) {
        final String userName = "smaavstp@gmail.com";
        final String password = "kphw tqrh dfpm owir";

        System.out.println("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); 

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(userName,password);

            }
        });
        try{
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail, true));
            message.setSubject("Prueba");
            message.setText("Hola " + nombre + ", muchas gracias por colaborar!\nTu contraseña de ingreso es "+contrasenia+" y tu usuario es " + nombre);
            System.out.println("sending...");
            Transport.send(message);
            System.out.println("Sent message successfully....");

        }catch (MessagingException me){
            System.out.println("Exception: "+me);

        }
    }
}
