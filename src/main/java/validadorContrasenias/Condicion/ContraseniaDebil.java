package validadorContrasenias.Condicion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ContraseniaDebil implements Condicion{
    private String path = "/contraseniasDebiles.txt";
    private String excepcion = "Contraseña debil";
    public ContraseniaDebil() {
    }

    @Override
    public boolean validarCondicion(String contrasenia) {

            try {
                File file = new File(System.getProperty("user.dir") + path);
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String linea = scanner.nextLine();
                    if (linea.equals(contrasenia)) {
                        throw new RuntimeException(excepcion);
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

