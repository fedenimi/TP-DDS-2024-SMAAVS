package validadorContrasenias.Condicion;

public class LongitudContrasenia implements Condicion{
    private Integer longitud;

    public LongitudContrasenia(Integer longitud) {
        this.longitud = longitud;
    }

    @Override
    public boolean validarCondicion(String contrasenia) {
        return contrasenia.length() >= longitud;
    }
}
