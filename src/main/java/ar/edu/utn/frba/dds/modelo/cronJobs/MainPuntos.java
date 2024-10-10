package ar.edu.utn.frba.dds.modelo.cronJobs;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.utils.Calculador;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;

import java.util.List;

public class MainPuntos {
    public static void main(String[] args) {
        RepositorioColaboradores repositorioColaboradores = ServiceLocator.instanceOf(RepositorioColaboradores.class);
        List<Colaborador> colaboradoresHumanos = repositorioColaboradores.getColaboradoresHumanos();

        colaboradoresHumanos.forEach(colaborador -> actualizarPuntosDe(colaborador, repositorioColaboradores));
    }

    public static void actualizarPuntosDe(Colaborador colaborador, RepositorioColaboradores repositorio) {
        colaborador.setPuntosDisponibles(Calculador.getInstance().puntaje(colaborador.getPuntuables(), colaborador.getPuntosCanjeados()));
        repositorio.modificar(colaborador);
    }
}


