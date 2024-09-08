package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.MedioDeContacto;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import io.github.flbulgarelli.jpa.extras.test.PersistenceTest;
import io.github.flbulgarelli.jpa.extras.test.SimplePersistenceTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
/*
public class TestPersistencia implements SimplePersistenceTest {
    private static EntityManagerFactory emf;
    private EntityManager em;

    @Before
    public void setupEntityManager() {
        emf = Persistence.createEntityManagerFactory("simple-persistence-unit");
        em = emf.createEntityManager();
    }

    @Test
    public void testPersistencia(){
        Colaborador colaborador = new Colaborador();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(colaborador);
        transaction.commit();

        Colaborador colaboradorEncontrado = em.find(Colaborador.class, colaborador.getId());
        Assertions.assertNotNull(colaboradorEncontrado);
    }
}

 */
public class TestPersistencia implements SimplePersistenceTest {
    private static EntityManagerFactory emf;
    private EntityManager em;
    @Before
    public void setupEntityManager() {
        emf = Persistence.createEntityManagerFactory("simple-persistence-unit");
        em = emf.createEntityManager();
    }
    @Test
    public void persistableIsInserted() {
        Colaborador persistable = new Colaborador();
        //persist(persistable);
        RepositorioColaboradores.getInstance().guardar(persistable);
        assertNotNull(persistable.getId());

        Optional<Colaborador> optCol = RepositorioColaboradores.getInstance().buscar(1L);
        assertNotNull(optCol.get());
    }

}
