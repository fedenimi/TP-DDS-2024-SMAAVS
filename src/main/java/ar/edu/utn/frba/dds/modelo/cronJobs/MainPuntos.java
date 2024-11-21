package ar.edu.utn.frba.dds.modelo.cronJobs;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.utils.Calculador;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;

public class MainPuntos implements WithSimplePersistenceUnit {
    public static void main(String[] args) {
        RepositorioColaboradores repositorioColaboradores = ServiceLocator.instanceOf(RepositorioColaboradores.class);
        List<Colaborador> colaboradores = repositorioColaboradores.buscarTodos();

        colaboradores.forEach(colaborador -> actualizarPuntosDe(colaborador, repositorioColaboradores));
    }

    public static void actualizarPuntosDe(Colaborador colaborador, RepositorioColaboradores repositorio) {
        colaborador.setPuntosDisponibles(Calculador.getInstance().puntaje(colaborador.getPuntuables(), colaborador.getPuntosCanjeados()));
        repositorio.modificar(colaborador);
        System.out.println("PUNTOS: " + Calculador.getInstance().puntaje(colaborador.getPuntuables(), colaborador.getPuntosCanjeados()));
        if(colaborador.getPuntuables().size() > 0) {
        System.out.println("ID DEL ULTIMO: " + colaborador.getPuntuables().get(colaborador.getPuntuables().size() - 1).getId());
        }
    }
}


