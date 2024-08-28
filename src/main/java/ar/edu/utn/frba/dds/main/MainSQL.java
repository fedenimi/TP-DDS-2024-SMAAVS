package ar.edu.utn.frba.dds.main;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

public class MainSQL implements WithSimplePersistenceUnit {
    public static void main(String[] args) {
        MainSQL instance = new MainSQL();
        instance.impactarEnBase();
    }

    public void impactarEnBase() {
        withTransaction(() -> {

        });
    }

}
