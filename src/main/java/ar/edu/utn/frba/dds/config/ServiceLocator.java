package ar.edu.utn.frba.dds.config;

import ar.edu.utn.frba.dds.controladores.ControladorFallaTecnica;
import ar.edu.utn.frba.dds.controladores.ControladorHeladeras;
import ar.edu.utn.frba.dds.controladores.ControladorHome;
import ar.edu.utn.frba.dds.controladores.ControladorRegistro;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.BuscadorDeTecnicos;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;

import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {
    private static Map<String, Object> instances = new HashMap<>();


    @SuppressWarnings("unchecked")
    public static <T> T instanceOf(Class<T> componentClass) {
        String componentName = componentClass.getName();

        if (!instances.containsKey(componentName)) {
            if(componentName.equals(ControladorHeladeras.class.getName())) {
                ControladorHeladeras instance = new ControladorHeladeras(instanceOf(RepositorioHeladeras.class));
                instances.put(componentName, instance);
            }
            else if (componentName.equals(RepositorioHeladeras.class.getName())) {
                RepositorioHeladeras instance = new RepositorioHeladeras();
                instances.put(componentName, instance);
            }
            else if (componentName.equals(ControladorHome.class.getName())) {
                ControladorHome instance = new ControladorHome();
                instances.put(componentName, instance);
            }
            else if (componentName.equals(ControladorFallaTecnica.class.getName())) {
                ControladorFallaTecnica instance = new ControladorFallaTecnica(instanceOf(BuscadorDeTecnicos.class));
                instances.put(componentName, instance);
            }
            else if (componentName.equals(BuscadorDeTecnicos.class.getName())) {
                BuscadorDeTecnicos instance = new BuscadorDeTecnicos();
                instances.put(componentName, instance);
            }
            else if (componentName.equals(ControladorRegistro.class.getName())) {
                ControladorRegistro instance = new ControladorRegistro();
                instances.put(componentName, instance);
            }
        }

        return (T) instances.get(componentName);
    }
}