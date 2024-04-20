package validadorContrasenias;

import validadorContrasenias.Validador;

public class Usuario {

    private String usuario;
    private String contrasenia;
    private Validador validador;
    public Usuario(String usuario) {
        this.usuario = usuario;
    }

    public void obtenerContrasenia(String contrasenia) {
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

