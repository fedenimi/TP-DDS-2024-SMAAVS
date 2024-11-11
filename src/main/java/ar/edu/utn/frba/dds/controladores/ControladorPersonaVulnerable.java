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
import ar.edu.utn.frba.dds.servicios.ServicePersonasVulnerables;
import io.javalin.http.Context;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

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
        PersonaVulnerable personaVulnerable = ServicePersonasVulnerables.crearPersonaVulnerable(context);
        RegistroDePersonasVulnerables registroDePersonasVulnerables = new RegistroDePersonasVulnerables();
        if(Objects.equals(context.formParam("padre"), "")) {
            ServicePersonasVulnerables.asignarTarjetaA(personaVulnerable, repositorioPersonasVulnerables);
            repositorioPersonasVulnerables.guardar(personaVulnerable);
        }
        else {
            Optional<PersonaVulnerable> padreOpt = repositorioPersonasVulnerables.buscarPor((context.formParam("padre")));
            if(padreOpt.isPresent()) {
                PersonaVulnerable padre = padreOpt.get();
                padre.agregarMenorACargo(personaVulnerable);
                personaVulnerable.setTarjeta(padre.getTarjeta());
            }
        }


        Colaborador colaborador = repositorioColaboradores.buscar(Long.parseLong(context.pathParam("id"))).get();
        registroDePersonasVulnerables.setColaborador(colaborador);
        registroDePersonasVulnerables.setRegistro(Registro.builder().personaVulnerable(personaVulnerable).fechaDeRegistro(LocalDate.now()).build());

        repositorioPuntuables.guardar(registroDePersonasVulnerables);

        colaborador.agregarPuntuable(registroDePersonasVulnerables);
        repositorioColaboradores.modificar(colaborador);

        
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
