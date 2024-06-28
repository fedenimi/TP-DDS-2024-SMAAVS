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
        user1.validarContrasenia("abcdefghijklmnñopqrstuvwxyzabcdefghijklmnñopqrstuvwxyz0123456789asd");
        Assert.assertEquals("abcdefghijklmnñopqrstuvwxyzabcdefghijklmnñopqrstuvwxyz0123456789asd", user1.getContrasenia());
    }

    @Test
    public void testContraseniaConEspacio() {
        user1.validarContrasenia("Buenas Tardes");
        Assert.assertEquals("Buenas Tardes", user1.getContrasenia());
    }

    @Test
    public void testContraseniaTop10k() {
        Assert.assertThrows("Contraseña debil", RuntimeException.class,()->{user1.validarContrasenia("12345678");});

        Assert.assertEquals(null, user1.getContrasenia());
    }

    @Test
    public void testContraseniaCorta() {
        Assert.assertThrows("La contraseña debe tener al menos 8 caracteres",RuntimeException.class, () ->{user1.validarContrasenia("wabila9");});
    }

    @Test
    public void testContraseniaIgualUsuario() {
        Assert.assertThrows("Contrasenia igual a usuario",RuntimeException.class, () -> {user1.validarContrasenia("jorgejorge");});
    }
}