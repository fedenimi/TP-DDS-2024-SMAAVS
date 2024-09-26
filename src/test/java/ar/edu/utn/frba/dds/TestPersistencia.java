package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.Documento;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.MedioDeContacto;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TipoDocumento;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioObjects;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import io.github.flbulgarelli.jpa.extras.test.PersistenceTest;
import io.github.flbulgarelli.jpa.extras.test.SimplePersistenceTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

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
    public void colaboradorPersistido() {
        Colaborador persistable = new Colaborador();
        RepositorioColaboradores.getInstance().guardar(persistable);
        assertNotNull(persistable.getId());

        Optional<Colaborador> optCol = RepositorioColaboradores.getInstance().buscar(1L);
        assertNotNull(optCol.get());
        System.out.println("El id es: " + optCol.get().getId());
        List<Colaborador> colaboradores = RepositorioColaboradores.getInstance().buscarTodos();
        assertEquals(1, colaboradores.size());
    }

    @Test
    public void colaboradorEliminado() {
        Colaborador persistable = new Colaborador();
        RepositorioColaboradores.getInstance().guardar(persistable);
        List<Colaborador> colaboradores = RepositorioColaboradores.getInstance().buscarTodos();
        assertEquals(1, colaboradores.size());
        RepositorioColaboradores.getInstance().eliminar(persistable);
        List<Colaborador> noColaboradores = RepositorioColaboradores.getInstance().buscarTodos();
        assertEquals(0, noColaboradores.size());
    }

    @Test
    public void buscarColaboradorPorDoc(){
        Colaborador persistable = new Colaborador();
        persistable.setDocumento(new Documento("1", TipoDocumento.DNI));
        RepositorioColaboradores.getInstance().guardar(persistable);
        Optional<Colaborador> colaborador = RepositorioColaboradores.getInstance().buscarPor("1", TipoDocumento.DNI);
        Assert.assertEquals(persistable, colaborador.get());
    }

    @Test
    public void colaboradorActualizado() {
        Colaborador persistable = new Colaborador();
        RepositorioColaboradores.getInstance().guardar(persistable);
        persistable.setApellido("perez");
        RepositorioColaboradores.getInstance().guardar(persistable);
        Optional<Colaborador> optCol = RepositorioColaboradores.getInstance().buscar(1L);
        assertEquals(persistable.getApellido(), optCol.get().getApellido());

    }

    @Test
    public void medioPersistido() {
        MedioDeContacto medioDeContacto = new MedioDeContacto();
        RepositorioObjects repositorioObjects = new RepositorioObjects();
        beginTransaction();
        repositorioObjects.guardar(medioDeContacto);
        commitTransaction();
        assertNotNull(medioDeContacto.getId());

        Optional<MedioDeContacto> optCol = repositorioObjects.buscar(1L);
        assertNotNull(optCol.get());
        System.out.println("El id es: " + optCol.get().getId());
        List<MedioDeContacto> medioDeContactos = repositorioObjects.buscarTodos();
        assertEquals(1, medioDeContactos.size());
    }

}
