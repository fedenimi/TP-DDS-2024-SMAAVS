package ar.edu.utn.frba.dds.controladores;

import io.javalin.http.Context;

public class ControladorRegistro {

    public void mostrarInicioDeSesion(Context context) {
        context.render("registro/inicioSesion.hbs");
    }

    public void guardarInicioDeSesion(Context context) {
        System.out.println("Inicio de sesión...");
        System.out.println(context.formParam("usuario"));
        System.out.println(context.formParam("contrasenia"));
        context.redirect("/3/home");
    }
    public void mostrarRegistro(Context context) {
        context.render("registro/registroHumana.hbs");
    }

    public void guardarRegistro(Context context) {
        System.out.println("Llegó esto: ");
        //PREGUNTAR:
        // SI EL NOMBRE ESTÁ EN NULL ==> ES JURÍDICA
        // SI NO                     ==> ES HUMANA
        System.out.println(context.formParam("nombre"));
        System.out.println(context.formParam("apellido"));
        System.out.println(context.formParam("mail"));
        System.out.println(context.formParam("usuario"));
        System.out.println(context.formParam("direccion"));
        System.out.println(context.formParam("whatsapp"));
        System.out.println(context.formParam("donar-viandas"));
        System.out.println(context.formParam("donar-dinero"));
        System.out.println(context.formParam("distribuir-viandas"));
        System.out.println(context.formParam("administrar-heladeras"));
        //TODO: el 3 está hardcodeado, tiene q dirigir a el id posta -->
        context.redirect("/3/home");
    }
}
