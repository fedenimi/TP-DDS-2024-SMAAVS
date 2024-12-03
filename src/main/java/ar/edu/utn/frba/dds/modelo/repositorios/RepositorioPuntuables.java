package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.Puntuable;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;
import java.util.Optional;

public class RepositorioPuntuables implements IRepositorio<Puntuable>, WithSimplePersistenceUnit {
    @Override
    public void guardar(Puntuable puntuable) {
        beginTransaction();
        entityManager().persist(puntuable);
        commitTransaction();
    }

    @Override
    public void eliminar(Puntuable puntuable) {
        beginTransaction();
        entityManager().remove(puntuable);
        commitTransaction();
    }

    @Override
    public Optional<Puntuable> buscar(Long id) {
        return Optional.ofNullable(entityManager().find(Puntuable.class, id));
    }

    @Override
    public List<Puntuable> buscarTodos() {
        return entityManager()
                .createQuery("from " + Puntuable.class.getName())
                .getResultList();
    }

    public void modificar(Puntuable puntuable) {
        withTransaction(() -> {
            entityManager().merge(puntuable);//UPDATE
        });
    }
}
