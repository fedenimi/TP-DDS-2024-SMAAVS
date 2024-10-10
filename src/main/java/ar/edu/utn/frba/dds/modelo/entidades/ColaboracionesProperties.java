package ar.edu.utn.frba.dds.modelo.entidades;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ColaboracionesProperties {
    private static ColaboracionesProperties instance = null;
    private Properties prop;


    public static ColaboracionesProperties getInstance() {
        if(instance == null) {
            instance = new ColaboracionesProperties();
        }
        return instance;
    }

    private ColaboracionesProperties() {
        this.prop = new Properties();
        this.readProperties();
    }

    private void readProperties() {
        try {
            InputStream input = new FileInputStream("colaboraciones.properties");
            this.prop.load(input);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String propertyFromName(String name) {
        return this.prop.getProperty(name, null);
    }
}
