package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.dtos.FraudeDTO;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.*;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.TipoNotificacion;
import ar.edu.utn.frba.dds.modelo.entidades.utils.CalculadorDeFechas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioAlertas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioTecnicos;
import ar.edu.utn.frba.dds.servicios.ServiceTopics;

import java.time.LocalDateTime;
import java.util.Optional;

public class ControladorMovimiento {

    private BuscadorDeTecnicos buscadorDeTecnicos = new BuscadorDeTecnicos(ServiceLocator.instanceOf(RepositorioTecnicos.class));
    private static ControladorMovimiento instance = null;
    public static ControladorMovimiento getInstance() {
        if(instance == null)
            instance = new ControladorMovimiento();
        return instance;
    }
    public void recibirFraude(FraudeDTO fraudeDTO) {
        Heladera heladera = null;

        Optional<Heladera> heladeraOptional = ServiceLocator.instanceOf(RepositorioHeladeras.class).buscar(Long.parseLong(fraudeDTO.getIdHeladera()));
        if (heladeraOptional.isPresent()) {
           heladera = heladeraOptional.get();
        }
        RegistradorDeIncidentes.getInstance().registrarIncidente(Estado.FRAUDE, heladera, buscadorDeTecnicos);
        System.out.println("Se recibio un fraude en la heladera: " + heladera.getId());
        Alerta fraude = CreadorAlerta.getInstance().crearAlerta(heladera, Estado.FRAUDE);

        ServiceLocator.instanceOf(RepositorioAlertas.class).guardar(fraude);
        ServiceTopics.accionarTopic(heladera, TipoNotificacion.DESPERFECTO);
    }
}
