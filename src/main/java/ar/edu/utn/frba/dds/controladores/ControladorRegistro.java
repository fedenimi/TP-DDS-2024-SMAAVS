package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.modelo.entidades.acceso.Permiso;
import ar.edu.utn.frba.dds.modelo.entidades.acceso.Usuario;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.*;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import ar.edu.utn.frba.dds.servicios.ServiceColaboradores;
import io.javalin.http.Context;

public class ControladorRegistro {
    private final RepositorioColaboradores repositorioColaboradores;

    public ControladorRegistro(RepositorioColaboradores repositorioColaboradores) {
        this.repositorioColaboradores = repositorioColaboradores;
    }

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
        System.out.println(context.formParam("tipo-documento"));
        System.out.println(context.formParam("nro-documento"));
        //TODO: el 3 está hardcodeado, tiene q dirigir a el id posta -->

        Usuario usuario = new Usuario();
        usuario.setNombre(context.formParam("usuario"));
        usuario.setContrasenia(context.formParam("contrasenia"));


        Colaborador colaborador = null;
        if(context.formParam("nombre") != null) {
            colaborador = ServiceColaboradores.crearColaboradorHumano(context, usuario);
            usuario.agregarPermiso(Permiso.HUMANA);
        } else {
            colaborador = ServiceColaboradores.crearColaboradorJuridico(context, usuario);
            usuario.agregarPermiso(Permiso.JURIDICA);
        }
        usuario.setColaboradorAsociado(colaborador);

        repositorioColaboradores.beginTransaction();
        repositorioColaboradores.guardar(colaborador);
        repositorioColaboradores.commitTransaction();

       context.redirect("/"+colaborador.getId()+"/home");

    }
}
