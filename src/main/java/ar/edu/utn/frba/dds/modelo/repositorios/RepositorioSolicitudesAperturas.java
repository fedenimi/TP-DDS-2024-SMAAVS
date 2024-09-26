package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.SolicitudApertura;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;
import java.util.Optional;

public class RepositorioSolicitudesAperturas implements IRepositorio<SolicitudApertura>, WithSimplePersistenceUnit {
    @Override
    public void guardar(SolicitudApertura solicitudApertura) {
        entityManager().persist(solicitudApertura);
    }

    @Override
    public void eliminar(SolicitudApertura solicitudApertura) {
        entityManager().remove(solicitudApertura);
    }

    @Override
    public Optional<SolicitudApertura> buscar(Long id) {
        return Optional.ofNullable(entityManager().find(SolicitudApertura.class, id));
    }

    @Override
    public List<SolicitudApertura> buscarTodos() {
        return entityManager()
                .createQuery("from " + SolicitudApertura.class.getName())
                .getResultList();
    }
}
