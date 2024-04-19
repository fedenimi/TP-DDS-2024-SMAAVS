package validador;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Validador {

    private static Validador instance;

    private Validador() {};

    public static Validador getInstance() {
        if(instance == null) {
            instance = new Validador();
        }
        return instance;
    }

    public static boolean validarContrasenia(String usuario, String contrasenia) {
        return instance.validarContraseniaDebil(contrasenia) &&
               instance.validarLongitud(contrasenia) &&
               instance.validarCoincidencia(usuario, contrasenia);
    }

    private boolean validarContraseniaDebil(String contrasenia) {
        try {
            File file = new File(System.getProperty("user.dir") + "/contraseniasDebiles.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                if (linea.equals(contrasenia)) {
                    return false;
            }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
            System.out.println(System.getProperty("user.dir"));
        }
        return true;
    }

    private boolean validarLongitud(String contrasenia) {
        return contrasenia.length() >= 8;
    }

    private boolean validarCoincidencia(String usuario, String contrasenia) {
        return !usuario.equals(contrasenia);
    }

}
