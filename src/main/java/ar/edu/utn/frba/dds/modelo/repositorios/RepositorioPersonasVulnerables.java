package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.personas.PersonaVulnerable;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;
import java.util.Optional;

public class RepositorioPersonasVulnerables implements IRepositorio<PersonaVulnerable>, WithSimplePersistenceUnit {
    @Override
    public void guardar(PersonaVulnerable personaVulnerable) {
        beginTransaction();
        entityManager().persist(personaVulnerable);
        commitTransaction();
    }

    @Override
    public void eliminar(PersonaVulnerable personaVulnerable) {
        beginTransaction();
        entityManager().remove(personaVulnerable);
        commitTransaction();
    }

    @Override
    public Optional<PersonaVulnerable> buscar(Long id) {
        return Optional.ofNullable(entityManager().find(PersonaVulnerable.class, id));
    }

    @Override
    public List<PersonaVulnerable> buscarTodos() {
        return entityManager()
                .createQuery("from " + PersonaVulnerable.class.getName())
                .getResultList();
    }
}
