package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorio;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorioAlertas;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor
public class RepositorioAlertas implements IRepositorio<Alerta>, WithSimplePersistenceUnit{
    List<Alerta> alertas = new ArrayList<>();
    private static RepositorioAlertas instance = null;
    public static RepositorioAlertas getInstance() {
        if(instance == null) {
            instance = new RepositorioAlertas();
        }
        return instance;
    }
    @Override
    public void guardar(Alerta alerta) {
        entityManager().persist(alerta);
    }

    @Override
    public void eliminar(Alerta alerta) {
        entityManager().remove(alerta);
    }

    @Override
    public Optional<Alerta> buscar(Long id) {
        return Optional.ofNullable(entityManager().find(Alerta.class, id));
    }

    @Override
    public List<Alerta> buscarTodos() {
        return entityManager()
                .createQuery("from " + Alerta.class.getName())
                .getResultList();
    }
}
