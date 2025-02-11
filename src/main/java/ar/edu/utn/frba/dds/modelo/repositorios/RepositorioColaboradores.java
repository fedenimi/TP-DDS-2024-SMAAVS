package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.MedioDeContacto;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TipoDeColaborador;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TipoDocumento;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorio;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorioColaboradores;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.Transient;
import java.util.List;
import java.util.Optional;

public class RepositorioColaboradores implements IRepositorio<Colaborador>, WithSimplePersistenceUnit {
    private List<Colaborador> colaboradores;
    private static RepositorioColaboradores instance = null;
    public static RepositorioColaboradores getInstance() {
        if(instance == null)
            instance = new RepositorioColaboradores();
        return instance;
    }
    public Optional<Colaborador> buscarPor(String doc, TipoDocumento tipoDoc) {
        try {
            Colaborador colaborador = entityManager()
                    .createQuery("from Colaborador c where c.documento.tipo = :tipo and c.documento.numero = :numero", Colaborador.class)
                    .setParameter("tipo", tipoDoc)
                    .setParameter("numero", doc)
                    .getSingleResult();

            return Optional.of(colaborador);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
    @Override
    public Optional<Colaborador> buscar(Long id) {
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
        withTransaction(() -> entityManager().remove(colaborador));
    }

    @Override
    public void guardar(Colaborador colaborador) {
        withTransaction(() -> entityManager().persist(colaborador));
    }

    public void modificar(Colaborador colaborador) {
        withTransaction(() -> {
            entityManager().merge(colaborador);
        });
    }

    public List<Colaborador> getColaboradoresHumanos() {
        return this.buscarTodos().stream().filter(colaborador -> colaborador.getTipoDeColaborador().equals(TipoDeColaborador.HUMANA)).toList();
    }
}
