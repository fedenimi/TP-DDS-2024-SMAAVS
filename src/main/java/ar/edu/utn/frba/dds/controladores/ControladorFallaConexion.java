package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.CreadorAlerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.IBuscadorDeTecnicos;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.RegistradorDeIncidentes;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores.ReceptorSensorTemperatura;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.TipoNotificacion;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioAlertas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioReceptoresTemperatura;
import ar.edu.utn.frba.dds.servicios.ServiceTopics;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class ControladorFallaConexion {
    private Integer cantidadMinutos;
    private IBuscadorDeTecnicos buscadorDeTecnicos;
    public void verificarConexiones() {
        List<ReceptorSensorTemperatura> receptores = RepositorioReceptoresTemperatura.getInstance().buscarTodos();
        for (ReceptorSensorTemperatura receptor : receptores) {
            if (this.fallo(receptor)) {
                Heladera heladera = receptor.getHeladera();
                RegistradorDeIncidentes.getInstance().registrarIncidente(Estado.FALLA_CONEXION, heladera, buscadorDeTecnicos);
                Alerta alerta = CreadorAlerta.getInstance().crearAlerta(heladera, Estado.FALLA_CONEXION);
                RepositorioAlertas.getInstance().guardar(alerta);
                ServiceTopics.accionarTopic(heladera, TipoNotificacion.DESPERFECTO);
            }
        }

    }

    private boolean fallo(ReceptorSensorTemperatura receptor) {
        if(receptor.getUltimaConexion() != null) return receptor.getUltimaConexion().plusMinutes(cantidadMinutos).isBefore(LocalDateTime.now());
        return receptor.getHeladera().getFechaYHoraInicio().plusMinutes(cantidadMinutos).isBefore(LocalDateTime.now());
    }
}
