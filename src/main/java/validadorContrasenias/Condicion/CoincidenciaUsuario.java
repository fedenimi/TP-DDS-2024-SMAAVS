package validadorContrasenias.Condicion;

public class CoincidenciaUsuario implements Condicion{
    private String usuario;

    public CoincidenciaUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean validarCondicion(String contrasenia) {
        return !usuario.equals(contrasenia);
    }
}
