package validadorContrasenias.Condicion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ContraseniaDebil implements Condicion{
    public ContraseniaDebil() {
    }

    @Override
    public boolean validarCondicion(String contrasenia) {

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
    }

