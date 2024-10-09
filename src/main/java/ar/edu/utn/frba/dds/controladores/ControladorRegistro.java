package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.modelo.entidades.acceso.Permiso;
import ar.edu.utn.frba.dds.modelo.entidades.acceso.Usuario;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.*;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioUsuarios;
import ar.edu.utn.frba.dds.servicios.ServiceColaboradores;
import io.javalin.http.Context;

import java.util.List;
import java.util.Optional;

public class ControladorRegistro {
    private final RepositorioColaboradores repositorioColaboradores;
    private final RepositorioUsuarios repositorioUsuarios;

    public ControladorRegistro(RepositorioColaboradores repositorioColaboradores, RepositorioUsuarios repositorioUsuarios) {
        this.repositorioColaboradores = repositorioColaboradores;
        this.repositorioUsuarios = repositorioUsuarios;
    }

    public void mostrarInicioDeSesion(Context context) {
        context.render("registro/inicioSesion.hbs");
    }

    public void guardarInicioDeSesion(Context context) {
        System.out.println("Inicio de sesión...");
        System.out.println(context.formParam("usuario"));

        Optional<Usuario> usuarioPosible = repositorioUsuarios.buscarPorNombre(context.formParam("usuario"));
        if(usuarioPosible.isEmpty()) {
            context.redirect("/inicioSesion");
            return;
        }

        Usuario usuario = usuarioPosible.get();
        if(!usuario.getContrasenia().equals(context.formParam("contrasenia"))) {
            System.out.println("Contraseña guardada: " + usuario.getContrasenia());
            System.out.println("Contraseña ingresada: " + context.formParam("contrasenia"));
            context.redirect("/inicioSesion");
            return;
        }
        System.out.println(context.formParam("contrasenia"));
        context.sessionAttribute("permisos", usuario.getPermisos());
        context.sessionAttribute("colaborador_id", usuario.getColaboradorAsociado().getId());
        context.redirect("/"+context.sessionAttribute("colaborador_id")+"/home");
    }
    public void mostrarRegistro(Context context) {
        context.render("registro/registroHumana.hbs");
    }

    public void guardarRegistro(Context context) {
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
        System.out.println("Roles del usuario: " + usuario.getPermisos());
        System.out.println("Formas de colaborar: " + usuario.getColaboradorAsociado().getFormasDeColaborar());
        repositorioColaboradores.beginTransaction();
        repositorioColaboradores.guardar(colaborador);
        repositorioColaboradores.commitTransaction();

        repositorioUsuarios.beginTransaction();
        repositorioUsuarios.guardar(usuario);
        repositorioUsuarios.commitTransaction();

        context.sessionAttribute("permisos", usuario.getPermisos());
        context.sessionAttribute("colaborador_id", usuario.getColaboradorAsociado().getId());
        List<Permiso> permisos = usuario.getPermisos();
        if(permisos.contains(Permiso.HUMANA)) {
            context.render("main/homeHumana.hbs");
        } else {
            context.render("main/homeJuridica.hbs");
        }

    }
}
