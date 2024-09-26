package ar.edu.utn.frba.dds.controladores;

import io.javalin.http.Context;
import java.util.HashMap;
import java.util.Map;

public class ControladorRegistro {
    public void mostrarRegistro(Context context) {
        context.render("registro/registroHumana.hbs");
    }

    public void guardarCampos(Context context) {
        System.out.println("Llegó esto: ");

        System.out.println(context.formParam("nombre"));
        System.out.println(context.formParam("apellido"));
        System.out.println(context.formParam("mail"));
        System.out.println(context.formParam("usuario"));
        System.out.println(context.formParam("donar-viandas"));
        System.out.println(context.formParam("donar-dinero"));
        System.out.println(context.formParam("distribuir-viandas"));
        System.out.println(context.formParam("administrar-heladeras"));
        context.redirect("/");
    }
}
