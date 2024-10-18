package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.OfrecerProducto;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;
import java.util.Optional;

public class RepositorioOfrecerProductos implements IRepositorio<OfrecerProducto>, WithSimplePersistenceUnit {

    @Override
    public void guardar(OfrecerProducto ofrecerProducto) {
        beginTransaction();
        entityManager().persist(ofrecerProducto);
        commitTransaction();
    }

    @Override
    public void eliminar(OfrecerProducto ofrecerProducto) {
        beginTransaction();
        entityManager().remove(ofrecerProducto);
        commitTransaction();
    }

    @Override
    public Optional<OfrecerProducto> buscar(Long id) {
        return Optional.ofNullable(entityManager().find(OfrecerProducto.class, id));
    }

    @Override
    public List<OfrecerProducto> buscarTodos() {
        return entityManager()
                .createQuery("from " + OfrecerProducto.class.getName())
                .getResultList();
    }
}
