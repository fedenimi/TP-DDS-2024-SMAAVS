package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TipoDocumento;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.personas.PersonaVulnerable;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

public class RepositorioPersonasVulnerables implements IRepositorio<PersonaVulnerable>, WithSimplePersistenceUnit {
    public Optional<PersonaVulnerable> buscarPor(String doc) {
        try {
            PersonaVulnerable personaVulnerable = entityManager()
                    .createQuery("from PersonaVulnerable c where c.documento.tipo = :tipo and c.documento.numero = :numero", PersonaVulnerable.class)
                    .setParameter("tipo", TipoDocumento.DNI)
                    .setParameter("numero", doc)
                    .getSingleResult();

            return Optional.of(personaVulnerable);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
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

    public void modificar(PersonaVulnerable personaVulnerable) {
        withTransaction(() -> {
            entityManager().merge(personaVulnerable);
        });
    }
}
