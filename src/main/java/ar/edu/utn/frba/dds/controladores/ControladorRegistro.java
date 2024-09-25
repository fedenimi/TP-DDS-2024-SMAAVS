package ar.edu.utn.frba.dds.controladores;

import io.javalin.http.Context;
public class ControladorRegistro {
    public void mostrarRegistro(Context context) {
        context.render("registro/registroHumana.hbs");
    }

    public void guardarCampos(Context context) {
        System.out.println(context.formParam("nombre"));
        System.out.println(context.formParam("apellido"));
        System.out.println(context.formParam("mail"));
        context.redirect("/registro2");
    }
}
