package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorio;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorioHeladeras;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioHeladeras implements IRepositorio<Heladera>, WithSimplePersistenceUnit {

    @Override
    public Optional<Heladera> buscar(Long id) {
        return Optional.ofNullable(entityManager().find(Heladera.class, id));
    }

    @Override
    public void guardar(Heladera heladera) {
        entityManager().persist(heladera);
    }

    @Override
    public void eliminar(Heladera heladera) {
        entityManager().remove(heladera);
    }

    @Override
    public List<Heladera> buscarTodos() {
        return entityManager()
                .createQuery("from " + Heladera.class.getName())
                .getResultList();
    }

}
