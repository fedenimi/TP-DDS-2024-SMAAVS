package ar.edu.utn.frba.dds.controladores;

import io.javalin.http.Context;
import java.util.HashMap;
import java.util.Map;

public class ControladorRegistro {
    public void mostrarRegistro(Context context) {
        Map<String, Object> model = new HashMap<>();
        model.put("Tipo", "Humana");
        context.render("registro/registroHumana.hbs", model);
    }

    public void guardarCampos(Context context) {
        System.out.println(context.formParam("nombre"));
        System.out.println(context.formParam("apellido"));
        System.out.println(context.formParam("mail"));
        context.redirect("/registro2");
    }
}
