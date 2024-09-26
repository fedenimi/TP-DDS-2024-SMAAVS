package ar.edu.utn.frba.dds.config;

import ar.edu.utn.frba.dds.controladores.*;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.BuscadorDeTecnicos;
import ar.edu.utn.frba.dds.modelo.repositorios.*;

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
            else if(componentName.equals(RepositorioOfrecerProductos.class.getName())) {
                RepositorioOfrecerProductos instance = new RepositorioOfrecerProductos();
                instances.put(componentName, instance);
            }
            else if(componentName.equals(ControladorProductos.class.getName())) {
                ControladorProductos instance = new ControladorProductos(instanceOf(RepositorioOfrecerProductos.class));
                instances.put(componentName, instance);
            }
            else if(componentName.equals(RepositorioColaboradores.class.getName())) {
                RepositorioColaboradores instance = new RepositorioColaboradores();
                instances.put(componentName, instance);
            }
            else if(componentName.equals(ControladorColaborador.class.getName())) {
                ControladorColaborador instance = new ControladorColaborador(instanceOf(RepositorioColaboradores.class));
                instances.put(componentName, instance);
            }
            else if(componentName.equals(RepositorioPuntuables.class.getName())) {
                RepositorioPuntuables instance = new RepositorioPuntuables();
                instances.put(componentName, instance);
            }
            else if(componentName.equals(ControladorDistribuirViandas.class.getName())) {
                ControladorDistribuirViandas instance = new ControladorDistribuirViandas(instanceOf(RepositorioPuntuables.class), instanceOf(RepositorioHeladeras.class));
                instances.put(componentName, instance);
            }
            else if(componentName.equals(ControladorDonacionDeDinero.class.getName())) {
                ControladorDonacionDeDinero instance = new ControladorDonacionDeDinero(instanceOf(RepositorioPuntuables.class));
                instances.put(componentName, instance);
            }
            else if(componentName.equals(ControladorDonacionDeViandas.class.getName())) {
                ControladorDonacionDeViandas instance = new ControladorDonacionDeViandas(instanceOf(RepositorioPuntuables.class));
                instances.put(componentName, instance);
            }
            else if(componentName.equals(RepositorioAlertas.class.getName())) {
                RepositorioAlertas instance = new RepositorioAlertas();
                instances.put(componentName, instance);
            }
            else if(componentName.equals(ControladorAlerta.class.getName())) {
                ControladorAlerta instance = new ControladorAlerta(instanceOf(RepositorioAlertas.class));
                instances.put(componentName, instance);
            }
            else if(componentName.equals(ControladorReportarFalla.class.getName())) {
                ControladorReportarFalla instance = new ControladorReportarFalla(instanceOf(RepositorioFallasTecnicas.class));
                instances.put(componentName, instance);
            }
            else if(componentName.equals(RepositorioFallasTecnicas.class.getName())) {
                RepositorioFallasTecnicas instance = new RepositorioFallasTecnicas();
                instances.put(componentName, instance);
            }
            else if(componentName.equals((RepositorioPersonasVulnerables.class.getName()))) {
                RepositorioPersonasVulnerables instance = new RepositorioPersonasVulnerables();
                instances.put(componentName, instance);
            }
            else if(componentName.equals(ControladorPersonaVulnerable.class.getName())) {
                ControladorPersonaVulnerable instance = new ControladorPersonaVulnerable(instanceOf(RepositorioPersonasVulnerables.class));
                instances.put(componentName, instance);
            }
            else if(componentName.equals(ControladorSuscripciones.class.getName())) {
                ControladorSuscripciones instance = new ControladorSuscripciones(instanceOf(RepositorioHeladeras.class));
                instances.put(componentName, instance);
            }
        }

        return (T) instances.get(componentName);
    }
}