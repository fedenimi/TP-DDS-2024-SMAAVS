package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.FallaTecnica;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorioFallasTecnicas;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

@Getter
public class RepositorioFallasTecnicas implements IRepositorioFallasTecnicas, WithSimplePersistenceUnit {
    private static RepositorioFallasTecnicas instance = null;
    public static RepositorioFallasTecnicas getInstance() {
        if(instance == null) {
            instance = new RepositorioFallasTecnicas();
        }
        return instance;
    }
    List<FallaTecnica> fallasTecnicas;

    @Override
    public void guardar(FallaTecnica fallaTecnica) {
        entityManager().persist(fallaTecnica);
    }

    @Override
    public void eliminar(FallaTecnica fallaTecnica) {
        entityManager().remove(fallaTecnica);
    }

    @Override
    public Optional<FallaTecnica> buscar(String id) {
        return Optional.ofNullable(entityManager().find(FallaTecnica.class, id));
    }

    @Override
    public List<FallaTecnica> buscarTodos() {
        return entityManager()
                .createQuery("from " + FallaTecnica.class.getName())
                .getResultList();
    }
}
