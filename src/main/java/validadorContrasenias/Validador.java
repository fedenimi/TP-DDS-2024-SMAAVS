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

    public String validarContrasenia(String contrasenia) {
        try {
            this.condicionesAValidar.stream().allMatch(c -> c.validarCondicion(contrasenia));
            return null;
        } catch (RuntimeException e) {
            return e.getMessage();
        }

    }
}
