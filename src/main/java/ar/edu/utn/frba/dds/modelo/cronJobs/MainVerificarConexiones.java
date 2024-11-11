package ar.edu.utn.frba.dds.modelo.cronJobs;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.controladores.ControladorFallaConexion;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.BuscadorDeTecnicos;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.IBuscadorDeTecnicos;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.Documento;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.MedioDeContacto;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TipoDeContacto;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TipoDocumento;
import ar.edu.utn.frba.dds.modelo.entidades.enviadores.EnviadorDeMail;
import ar.edu.utn.frba.dds.modelo.entidades.enviadores.Llamador;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Tecnico;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioAlertas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioTecnicos;
import org.junit.Assert;

public class MainVerificarConexiones {
    public static void main(String[] args) {
        IBuscadorDeTecnicos buscadorDeTecnicos = new BuscadorDeTecnicos(ServiceLocator.instanceOf(RepositorioTecnicos.class));
        ControladorFallaConexion controladorFallaConexion = new ControladorFallaConexion(5, buscadorDeTecnicos);
        Llamador.getInstance().setEnviadorDeMail(new EnviadorDeMail());
        controladorFallaConexion.verificarConexiones();
        System.out.println("Hola");
    }
}
