package validadorDeContraseniasTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import validadorContrasenias.Condicion.CoincidenciaUsuario;
import validadorContrasenias.Condicion.ContraseniaDebil;
import validadorContrasenias.Condicion.LongitudContrasenia;
import validadorContrasenias.Usuario;
import validadorContrasenias.Validador;

public class TestContrasenias {
    private Usuario user1 = new Usuario("jorgejorge");
    CoincidenciaUsuario coincidenciaUsuario = new CoincidenciaUsuario(user1.getUsuario());
    ContraseniaDebil contraseniaDebil = new ContraseniaDebil();
    LongitudContrasenia longitudContrasenia = new LongitudContrasenia(8);
    Validador validador = new Validador(coincidenciaUsuario, contraseniaDebil, longitudContrasenia);

    @Before
    public void initializer()   {
    user1.setValidador(validador);

    }

    @Test
    public void testContrasenia64Caracteres() {
        user1.obtenerContrasenia("abcdefghijklmnñopqrstuvwxyzabcdefghijklmnñopqrstuvwxyz0123456789asd");
        Assert.assertEquals("abcdefghijklmnñopqrstuvwxyzabcdefghijklmnñopqrstuvwxyz0123456789asd", user1.getContrasenia());
    }

    @Test
    public void testContraseniaConEspacio() {
        user1.obtenerContrasenia("Buenas Tardes");
        Assert.assertEquals("Buenas Tardes", user1.getContrasenia());
    }

    @Test
    public void testContraseniaTop10k() {
        user1.obtenerContrasenia("12345678");
        Assert.assertEquals(null, user1.getContrasenia());
    }

    @Test
    public void testContraseniaCorta() {
        user1.obtenerContrasenia("wabila9");
        Assert.assertEquals(null, user1.getContrasenia());
    }

    @Test
    public void testContraseniaIgualUsuario() {
        user1.obtenerContrasenia("jorgejorge");
        Assert.assertEquals(null, user1.getContrasenia());
    }
}