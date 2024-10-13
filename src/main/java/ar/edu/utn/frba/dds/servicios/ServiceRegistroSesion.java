package ar.edu.utn.frba.dds.servicios;

import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TipoDocumento;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioUsuarios;
import io.javalin.http.Context;

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
}
