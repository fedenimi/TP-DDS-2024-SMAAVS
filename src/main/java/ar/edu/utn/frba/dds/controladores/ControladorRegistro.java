package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.exceptions.DocumentoRepetidoException;
import ar.edu.utn.frba.dds.modelo.entidades.acceso.Permiso;
import ar.edu.utn.frba.dds.modelo.entidades.acceso.Usuario;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.*;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioUsuarios;
import ar.edu.utn.frba.dds.servicios.ServiceColaboradores;
import ar.edu.utn.frba.dds.servicios.ServiceRegistroSesion;
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
            this.mostrarErrorUsuarioInexistente(context);
            return;
        }

        Usuario usuario = usuarioPosible.get();
        if(!usuario.getContrasenia().equals(context.formParam("contrasenia"))) {
            this.mostrarErrorContraseniaIncorrecta(context);
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
        if(ServiceRegistroSesion.esUsuarioRepetido(context, repositorioUsuarios)) {
            mostrarErrorUsuarioRepetido(context);
            return;
        }
        usuario.setNombre(context.formParam("usuario"));
        usuario.setContrasenia(context.formParam("contrasenia"));


        Colaborador colaborador = null;
        if(context.formParam("nombre") != null) {
            if(!ServiceRegistroSesion.esDocumentoUnico(context, repositorioColaboradores)) {
                mostrarErrorDocumentoRepetido(context);
                return;
            }
            if(ServiceRegistroSesion.sinMediosDeContacto(context)) {
                mostrarErrorAgregarMedioDeContacto(context);
                return;
            }
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

        context.redirect("/inicioSesion");

    }

    public void mostrarErrorDocumentoRepetido(Context context) {
        context.render("registro/errorDocumentoRepetido.hbs");
    }

    public void mostrarErrorUsuarioInexistente(Context context) {
        context.render("registro/errorUsuarioInexistente.hbs.hbs");
    }

    public void mostrarErrorContraseniaIncorrecta(Context context) {
        context.render("registro/errorContraseniaIncorrecta.hbs");
    }

    public void mostrarErrorUsuarioRepetido(Context context) {
        context.render("registro/errorUsuarioRepetido.hbs");
    }

    public void mostrarErrorAgregarMedioDeContacto(Context context) {
        context.render("registro/errorAgregarMedioDeContacto.hbs");
    }


}
