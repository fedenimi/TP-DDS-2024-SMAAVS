package validadorDeContraseniasTest;

import org.junit.Assert;
import validador.Usuario;

public class Test {

    @org.junit.Test
    public void testContrasenia64Caracteres() {
        Usuario usuario1 = new Usuario("jorge", "abcdefghijklmnñopqrstuvwxyzabcdefghijklmnñopqrstuvwxyz0123456789asd");
        Assert.assertEquals("abcdefghijklmnñopqrstuvwxyzabcdefghijklmnñopqrstuvwxyz0123456789asd", usuario1.getContrasenia());
    }

    @org.junit.Test
    public void testContraseniaConEspacio() {
        Usuario usuario1 = new Usuario("jorge", "Buenas Tardes");
        Assert.assertEquals("Buenas Tardes", usuario1.getContrasenia());
    }

    @org.junit.Test
    public void testContraseniaTop10k() {
        Usuario usuario1 = new Usuario("jorge", "12345678");
        Assert.assertEquals(null, usuario1.getContrasenia());
    }

    @org.junit.Test
    public void testContraseniaCorta() {
        Usuario usuario1 = new Usuario("jorge", "lm10");
        Assert.assertEquals(null, usuario1.getContrasenia());
    }

    @org.junit.Test
    public void testContraseniaIgualUsuario() {
        Usuario usuario1 = new Usuario("jorgepedro", "jorgepedro");
        Assert.assertEquals(null, usuario1.getContrasenia());
    }
}