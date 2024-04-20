package validadorContrasenias;

import validadorContrasenias.Condicion.Condicion;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Validador {
    List<Condicion> condicionesAValidar = new ArrayList<>();
    // private static Validador instance;
    public Validador(Condicion... condiciones) {
    this.condicionesAValidar.addAll(List.of(condiciones));
    };

    /*
    public static Validador getInstance() {
        if(instance == null) {
            instance = new Validador();
        }
        return instance;
    }
    */


    public boolean validarContrasenia(String contrasenia) {
        return this.condicionesAValidar.stream().allMatch(c -> c.validarCondicion(contrasenia));
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


    private boolean validarCoincidencia(String usuario, String contrasenia) {
        return !usuario.equals(contrasenia);
    }

}
