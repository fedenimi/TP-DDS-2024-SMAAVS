package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.dtos.FallaTecnicaDTO;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Registro;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.CreadorFallaTecnica;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.FallaTecnica;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.RegistradorDeIncidentes;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.utils.CalculadorDeFechas;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorioColaboradores;
import ar.edu.utn.frba.dds.modelo.repositorios.interfaces.IRepositorioHeladeras;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
public class ControladorFallaTecnica {
    private IRepositorioColaboradores repositorioColaboradores;
    private IRepositorioHeladeras repositorioHeladeras;
    public void recibirFallaTecnica(FallaTecnicaDTO fallaTecnicaDTO) {
        Colaborador reportador = null;
        LocalDateTime fechaYHora;
        Heladera heladera = null;

        Optional<Colaborador> reportadorOptional = repositorioColaboradores.buscarPor(fallaTecnicaDTO.getDoc(), fallaTecnicaDTO.getTipoDoc());
        if (reportadorOptional.isPresent()) reportador = reportadorOptional.get();
        fechaYHora = CalculadorDeFechas.getInstance().stringToLocalDateTime(fallaTecnicaDTO.getFechaYHora());
        Optional<Heladera> heladeraOptional = repositorioHeladeras.buscar(fallaTecnicaDTO.getIdHeladera());
        if (heladeraOptional.isPresent()) heladera = heladeraOptional.get();

        RegistradorDeIncidentes.getInstance().registrarIncidente(Estado.FALLA_TECNICA, heladera);

        FallaTecnica fallaTecnica = CreadorFallaTecnica.getInstance().crearFallaTecnica(heladera, reportador, fallaTecnicaDTO.getDescripcion(), fallaTecnicaDTO.getFoto(), fechaYHora);
        //TODO: Guardar falla tecnica
    }
}
