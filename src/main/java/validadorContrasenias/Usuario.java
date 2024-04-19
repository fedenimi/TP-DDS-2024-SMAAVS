package validador;

public class Usuario {

    private String usuario;
    private String contrasenia;

    Validador validador = Validador.getInstance();

    public Usuario(String usuario, String contrasenia) {
        this.usuario = usuario;
        if(validador.validarContrasenia(usuario, contrasenia)) {
            this.contrasenia = contrasenia;
        }
    }

    public String getContrasenia() {
        return contrasenia;
    }

}

