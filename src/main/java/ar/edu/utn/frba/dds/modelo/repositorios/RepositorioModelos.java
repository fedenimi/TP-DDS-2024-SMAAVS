package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.ModeloHeladera;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;
import java.util.Optional;

public class RepositorioModelos implements IRepositorio<ModeloHeladera>, WithSimplePersistenceUnit {

    @Override
    public void guardar(ModeloHeladera modeloHeladera) {
        beginTransaction();
        entityManager().persist(modeloHeladera);
        commitTransaction();
    }

    @Override
    public void eliminar(ModeloHeladera modeloHeladera) {
        beginTransaction();
        entityManager().remove(modeloHeladera);
        commitTransaction();
    }

    @Override
    public Optional<ModeloHeladera> buscar(Long id) {
        return Optional.ofNullable(entityManager().find(ModeloHeladera.class, id));
    }

    @Override
    public List<ModeloHeladera> buscarTodos() {
        return entityManager()
                .createQuery("from " + ModeloHeladera.class.getName())
                .getResultList();
    }
}
