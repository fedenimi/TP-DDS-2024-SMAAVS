package ar.edu.utn.frba.dds.controladores;

import io.javalin.http.Context;

public class ControladorHome {
    public void mostrarLanding(Context context) {
        context.render("main/landing.hbs");
    }
}
