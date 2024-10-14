package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Tecnico;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;
import java.util.Optional;

public class RepositorioTecnicos implements IRepositorio<Tecnico>, WithSimplePersistenceUnit {
    @Override
    public void guardar(Tecnico tecnico) {
        beginTransaction();
        entityManager().persist(tecnico);
        commitTransaction();
    }

    @Override
    public void eliminar(Tecnico tecnico) {
        beginTransaction();
        entityManager().remove(tecnico);
        commitTransaction();
    }

    @Override
    public Optional<Tecnico> buscar(Long id) {
        return Optional.ofNullable(entityManager().find(Tecnico.class, id));
    }

    @Override
    public List<Tecnico> buscarTodos() {
        return entityManager()
                .createQuery("from " + Tecnico.class.getName())
                .getResultList();
    }
}
