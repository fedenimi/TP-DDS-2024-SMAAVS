package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.FallaTecnica;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorio;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorioFallasTecnicas;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor
public class RepositorioFallasTecnicas implements IRepositorio<FallaTecnica>, WithSimplePersistenceUnit {
    List<FallaTecnica> fallasTecnicas;
    private static RepositorioFallasTecnicas instance = null;
    public static RepositorioFallasTecnicas getInstance() {
        if(instance == null) {
            instance = new RepositorioFallasTecnicas();
        }
        return instance;
    }

    @Override
    public void guardar(FallaTecnica fallaTecnica) {
        beginTransaction();
        entityManager().persist(fallaTecnica);
        commitTransaction();
    }


    @Override
    public void eliminar(FallaTecnica fallaTecnica) {
        beginTransaction();
        entityManager().remove(fallaTecnica);
        commitTransaction();
    }

    @Override
    public Optional<FallaTecnica> buscar(Long id) {
        return Optional.ofNullable(entityManager().find(FallaTecnica.class, id));
    }

    @Override
    public List<FallaTecnica> buscarTodos() {
        return entityManager()
                .createQuery("from " + FallaTecnica.class.getName())
                .getResultList();
    }
}
