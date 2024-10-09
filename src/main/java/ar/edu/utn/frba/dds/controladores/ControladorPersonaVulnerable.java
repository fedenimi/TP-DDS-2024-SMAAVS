package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.RegistroDePersonasVulnerables;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Registro;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.Documento;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TipoDocumento;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.Direccion;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.personas.PersonaVulnerable;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioPersonasVulnerables;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioPuntuables;
import io.javalin.http.Context;

import java.time.LocalDate;

public class ControladorPersonaVulnerable implements ICrudViewsHandler{
    private RepositorioPersonasVulnerables repositorioPersonasVulnerables;
    private RepositorioColaboradores repositorioColaboradores;
    private RepositorioPuntuables repositorioPuntuables;

    public ControladorPersonaVulnerable(RepositorioPersonasVulnerables repositorioPersonasVulnerables, RepositorioColaboradores repositorioColaboradores, RepositorioPuntuables repositorioPuntuables) {
        this.repositorioPersonasVulnerables = repositorioPersonasVulnerables;
        this.repositorioColaboradores = repositorioColaboradores;
        this.repositorioPuntuables = repositorioPuntuables;
    }
    @Override
    public void index(Context context) {
        context.render("colaboraciones/regPersona.hbs");
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {
        context.render("colaboraciones/regPersona.hbs");
    }

    @Override
    public void save(Context context) {
        PersonaVulnerable personaVulnerable = new PersonaVulnerable();
        RegistroDePersonasVulnerables registroDePersonasVulnerables = new RegistroDePersonasVulnerables();
        personaVulnerable.setNombre(context.formParam("nombre") + " " + context.formParam("apellido"));
        personaVulnerable.setFechaDeNacimiento(LocalDate.parse(context.formParam("fechaDeNacimiento")));
        personaVulnerable.setFechaDeRegistro(LocalDate.now());
        personaVulnerable.setDocumento(Documento.builder().numero(context.formParam("documento")).tipo(TipoDocumento.valueOf(context.formParam("tipoDocumento"))).build());
        personaVulnerable.setDomicilio(Direccion.builder().build());
        // TODO: ver domicilio en form y lista de personas a cargo

        repositorioPersonasVulnerables.beginTransaction();
        repositorioPersonasVulnerables.guardar(personaVulnerable);
        repositorioPersonasVulnerables.commitTransaction();

        Colaborador colaborador = repositorioColaboradores.buscar(Long.parseLong(context.pathParam("id"))).get();
        registroDePersonasVulnerables.setColaborador(colaborador);
        registroDePersonasVulnerables.setRegistro(Registro.builder().personaVulnerable(personaVulnerable).fechaDeRegistro(LocalDate.now()).build());

        repositorioPuntuables.beginTransaction();
        repositorioPuntuables.guardar(registroDePersonasVulnerables);
        repositorioPuntuables.commitTransaction();
        colaborador.agregarPuntuable(registroDePersonasVulnerables);
        repositorioColaboradores.beginTransaction();
        repositorioColaboradores.modificar(colaborador);
        repositorioColaboradores.commitTransaction();
        
        context.redirect("/"+context.pathParam("id")+"/home");
    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }

    @Override
    public void delete(Context context) {

    }
}
