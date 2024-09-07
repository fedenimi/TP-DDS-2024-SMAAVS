package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.Barrio;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import io.github.flbulgarelli.jpa.extras.test.PersistenceTest;
import io.github.flbulgarelli.jpa.extras.test.SimplePersistenceTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.json.XMLTokener.entity;
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

public class TestPersistencia implements PersistenceTest, WithSimplePersistenceUnit {
    @Test
    public void persistableIsInserted() {
        Alerta persistable = new Alerta();

        assertNull(persistable.getId());
        persist(persistable);
        assertNotNull(persistable.getId());
    }
}
