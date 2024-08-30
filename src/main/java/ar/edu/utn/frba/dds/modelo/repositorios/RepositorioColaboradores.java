package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorioColaboradores;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;
import java.util.Optional;

public class RepositorioColaboradores implements IRepositorioColaboradores, WithSimplePersistenceUnit {
    private List<Colaborador> colaboradores;
    private static RepositorioColaboradores instance = null;
    public static RepositorioColaboradores getInstance() {
        if(instance == null)
            instance = new RepositorioColaboradores();
        return instance;
    }

    @Override
    public Optional<Colaborador> buscarPor(String doc, String tipoDoc) {
        return null;
    }

    @Override
    public Optional<Colaborador> buscar(String id) {
        return Optional.ofNullable(entityManager().find(Colaborador.class, id));
    }

    @Override
    public List<Colaborador> buscarTodos() {
        return entityManager()
                .createQuery("from " + Colaborador.class.getName())
                .getResultList();
    }

    @Override
    public void eliminar(Colaborador colaborador) {
        entityManager().remove(colaborador);
    }

    @Override
    public void guardar(Colaborador colaborador) {
        entityManager().persist(colaborador);
    }
}
