package ar.edu.utn.frba.dds.servicios;

import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TipoDocumento;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioUsuarios;
import io.javalin.http.Context;
import validadorContrasenias.Condicion.CoincidenciaUsuario;
import validadorContrasenias.Condicion.ContraseniaDebil;
import validadorContrasenias.Condicion.LongitudContrasenia;
import validadorContrasenias.Validador;

import java.util.Optional;

public class ServiceRegistroSesion {
    public static boolean esDocumentoUnico(Context context, RepositorioColaboradores repositorioColaboradores) {
        Optional<Colaborador> posibleColaborador = repositorioColaboradores.buscarPor(context.formParam("nro-documento"), TipoDocumento.valueOf(context.formParam("tipo-documento")));

        return posibleColaborador.isEmpty();
    }

    public static boolean sinMediosDeContacto(Context context) {
        System.out.println("TELEFONO:" + context.formParam("telefono"));
        System.out.println("MAIL:" + context.formParam("mail"));
        System.out.println("WHATSAPP:" + context.formParam("whatsapp"));
        return context.formParam("telefono") == "" && context.formParam("mail") == "" && context.formParam("whatsapp") == "";
    }

    public static boolean esUsuarioRepetido(Context context, RepositorioUsuarios repositorioUsuarios) {
        return repositorioUsuarios.buscarPorNombre(context.formParam("usuario")).isPresent();
    }

    public static String errorContrasenia(String contrasenia, String usuario) {
        Validador validador = new Validador(new CoincidenciaUsuario(usuario), new LongitudContrasenia(8), new ContraseniaDebil());
        return validador.validarContrasenia(contrasenia);
    }
}
