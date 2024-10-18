package ar.edu.utn.frba.dds.modelo.repositorios;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.MedioDeContacto;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class RepositorioObjects implements WithSimplePersistenceUnit {
    public void guardar(MedioDeContacto o) {
        beginTransaction();
        entityManager().persist(o);
        commitTransaction();
    }



    public void eliminar(MedioDeContacto o) {
        beginTransaction();
        entityManager().remove(o);
        commitTransaction();
    }



    public Optional<MedioDeContacto> buscar(Long id) {
        return Optional.ofNullable(entityManager().find(MedioDeContacto.class, id));
    }

    public List<MedioDeContacto> buscarTodos() {
        return entityManager()
                .createQuery("from " + MedioDeContacto.class.getName())
                .getResultList();
    }
}
