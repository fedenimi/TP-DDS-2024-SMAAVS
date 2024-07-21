package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.dtos.FraudeDTO;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.CreadorAlerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.IBuscadorDeTecnicos;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.RegistradorDeIncidentes;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import ar.edu.utn.frba.dds.modelo.entidades.utils.CalculadorDeFechas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;

import java.time.LocalDateTime;
import java.util.Optional;

public class ControladorMovimiento {
    private IBuscadorDeTecnicos buscadorDeTecnicos;
    public void recibirFraude(FraudeDTO fraudeDTO) {
        LocalDateTime fechaYHora;
        Heladera heladera = null;

        fechaYHora = CalculadorDeFechas.getInstance().stringToLocalDateTime(fraudeDTO.getFechaYHora());
        Optional<Heladera> heladeraOptional = RepositorioHeladeras.getInstance().buscar(fraudeDTO.getIdHeladera());
        if (heladeraOptional.isPresent()) {
           heladera = heladeraOptional.get();
        }

        RegistradorDeIncidentes.getInstance().registrarIncidente(Estado.FRAUDE, heladera, buscadorDeTecnicos);

        Alerta fraude = CreadorAlerta.getInstance().crearAlerta(heladera, Estado.FRAUDE);

        //TODO: Guardar fraude
    }
}
