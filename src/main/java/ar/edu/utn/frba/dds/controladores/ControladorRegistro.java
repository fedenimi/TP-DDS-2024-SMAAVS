package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.exceptions.DocumentoRepetidoException;
import ar.edu.utn.frba.dds.modelo.entidades.acceso.Permiso;
import ar.edu.utn.frba.dds.modelo.entidades.acceso.Usuario;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.*;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Tecnico;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioUsuarios;
import ar.edu.utn.frba.dds.server.App;
import ar.edu.utn.frba.dds.servicios.ServiceColaboradores;
import ar.edu.utn.frba.dds.servicios.ServiceRegistroSesion;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ControladorRegistro {
    private final RepositorioColaboradores repositorioColaboradores;
    private final RepositorioUsuarios repositorioUsuarios;
    private static final Logger logger = LoggerFactory.getLogger(ControladorRegistro.class);
    public ControladorRegistro(RepositorioColaboradores repositorioColaboradores, RepositorioUsuarios repositorioUsuarios) {
        this.repositorioColaboradores = repositorioColaboradores;
        this.repositorioUsuarios = repositorioUsuarios;
    }

    public void mostrarInicioDeSesion(Context context) {
        context.render("registro/inicioSesion.hbs");
    }

    public void guardarInicioDeSesion(Context context) {
        Optional<Usuario> usuarioPosible = repositorioUsuarios.buscarPorNombre(context.formParam("usuario"));
        if(usuarioPosible.isEmpty()) {
            this.mostrarErrorUsuarioInexistente(context);
            logger.atInfo().log("El usuario " + context.formParam("usuario") + " no existe");
            return;
        }

        Usuario usuario = usuarioPosible.get();
        if(!usuario.getContrasenia().equals(context.formParam("contrasenia"))) {
            this.mostrarErrorContraseniaIncorrecta(context);
            logger.atInfo().log("La contraseña de" + usuario.getNombre() + "es incorrecta");
            return;
        }
        context.sessionAttribute("permisos", usuario.getPermisos());
        Colaborador colaborador = usuario.getColaboradorAsociado();
        Tecnico tecnico = usuario.getTecnicoAsociado();
        Long id = -1L;
        if (colaborador != null) {
            id = colaborador.getId();
            logger.atInfo().log("El usuario " + usuario.getNombre() + " ha iniciado sesión");
        }
        if (tecnico != null) {
            id = tecnico.getId();
            logger.atInfo().log("Un técnico ha ingresado al sistema.");
        }
        context.sessionAttribute("colaborador_id", id);
        context.redirect("/"+context.sessionAttribute("colaborador_id")+"/home");
    }
    public void mostrarRegistro(Context context) {
        context.render("registro/registroHumana.hbs");
    }

    public void guardarRegistro(Context context) {
        Usuario usuario = new Usuario();
        if(ServiceRegistroSesion.esUsuarioRepetido(context, repositorioUsuarios)) {
            mostrarErrorUsuarioRepetido(context);
            logger.atInfo().log("El nombre de usuario ya se encuentra registrado");
            return;
        }
        usuario.setNombre(context.formParam("usuario"));

        System.out.println(context.formParam("contrasenia"));

        if(!ServiceRegistroSesion.errorContrasenia(context.formParam("contrasenia"), context.formParam("usuario")).isEmpty()) {
            mostrarErrorContraseniaInvalida(context, ServiceRegistroSesion.errorContrasenia(context.formParam("contrasenia"), context.formParam("usuario")));
            logger.atInfo().log("La contraseña de" + usuario.getNombre() + "no cumple con los requisitos");
            return;
        }
        usuario.setContrasenia(context.formParam("contrasenia"));

        Colaborador colaborador = null;
        if(context.formParam("nombre") != null) {
            if(!ServiceRegistroSesion.esDocumentoUnico(context, repositorioColaboradores)) {
                mostrarErrorDocumentoRepetido(context);
                logger.atInfo().log("El documento de" + usuario.getNombre() + "ya se encuentra registrado");
                return;
            }
            if(ServiceRegistroSesion.sinMediosDeContacto(context)) {
                mostrarErrorAgregarMedioDeContacto(context);
                logger.atInfo().log("El usuario" + usuario.getNombre() + "no tiene medios de contacto");
                return;
            }
            colaborador = ServiceColaboradores.crearColaboradorHumano(context, usuario, repositorioColaboradores);
            usuario.agregarPermiso(Permiso.HUMANA);
        } else {
            colaborador = ServiceColaboradores.crearColaboradorJuridico(context, usuario);
            usuario.agregarPermiso(Permiso.JURIDICA);
        }
        usuario.setColaboradorAsociado(colaborador);
        repositorioColaboradores.guardar(colaborador);

        repositorioUsuarios.guardar(usuario);
        logger.atInfo().log("Se ha registrado el usuario " + usuario.getNombre());
        context.redirect("/inicioSesion");
    }

    public void mostrarErrorDocumentoRepetido(Context context) {
        context.render("registro/errorDocumentoRepetido.hbs");
    }

    public void mostrarErrorUsuarioInexistente(Context context) {
        context.render("registro/errorUsuarioInexistente.hbs");
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

    public void mostrarErrorContraseniaInvalida(Context context, String error) {
        Map<String, Object> model = new HashMap<>();
        model.put("error", error);
        System.out.println(error);
        context.render("registro/errorContraseniaInvalida.hbs", model);
    }


}
