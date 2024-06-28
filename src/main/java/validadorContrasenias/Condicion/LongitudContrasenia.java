package validadorContrasenias.Condicion;

public class LongitudContrasenia implements Condicion {
    private Integer longitud;
    private String excepcion = "La contraseña debe tener al menos 8 caracteres";


    public LongitudContrasenia(Integer longitud) {
        this.longitud = longitud;
    }

    @Override
    public boolean validarCondicion(String contrasenia) {
        if (contrasenia.length() < longitud) {
            throw new RuntimeException(excepcion);
        } else {
            return true;
        }
    }
}
