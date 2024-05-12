package validadorContrasenias;

public class Usuario {

    private String usuario;
    private String contrasenia;
    private Validador validador;
    public Usuario(String usuario) {
        this.usuario = usuario;
    }

    public void validarContrasenia(String contrasenia) {
        if(validador.validarContrasenia(contrasenia)) {
            this.contrasenia = contrasenia;
        }
    }
    public String getContrasenia() {
        return contrasenia;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setValidador(Validador validador) {
        this.validador = validador;
    }
}

