package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.CreadorAlerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.IBuscadorDeTecnicos;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.RegistradorDeIncidentes;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores.SensorTemperatura;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioAlertas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioSensoresTemperatura;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorioAlertas;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorioSensoresTemperatura;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class ControladorFallaConexion {
    private Integer cantidadMinutos;
    private IBuscadorDeTecnicos buscadorDeTecnicos;
    public void verificarConexiones() {
        List<SensorTemperatura> sensores = RepositorioSensoresTemperatura.getInstance().buscarTodos();
        for (SensorTemperatura sensor : sensores) {
            if (this.fallo(sensor)) {
                Heladera heladera = sensor.getHeladera();
                RegistradorDeIncidentes.getInstance().registrarIncidente(Estado.FALLA_CONEXION, heladera, buscadorDeTecnicos);
                Alerta alerta = CreadorAlerta.getInstance().crearAlerta(heladera, Estado.FALLA_CONEXION);
                RepositorioAlertas.getInstance().guardar(alerta);
            }
        }

    }

    private boolean fallo(SensorTemperatura sensor) {
        if(sensor.getUltimaConexion() != null) return sensor.getUltimaConexion().plusMinutes(cantidadMinutos).isBefore(LocalDateTime.now());
        return sensor.getHeladera().getFechaYHoraInicio().plusMinutes(cantidadMinutos).isBefore(LocalDateTime.now());
    }
}
