package validadorContrasenias.Condicion;

public class CoincidenciaUsuario implements Condicion {
    private String usuario;
    private String excepcion = "Contrasenia igual a usuario";

    public CoincidenciaUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean validarCondicion(String contrasenia) {
        if (usuario.equals(contrasenia)) {
            throw new RuntimeException(excepcion);
        } else return true;
    }
}
