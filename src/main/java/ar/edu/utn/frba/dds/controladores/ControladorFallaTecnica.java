package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.dtos.FallaTecnicaDTO;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.CreadorFallaTecnica;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.FallaTecnica;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.IBuscadorDeTecnicos;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.RegistradorDeIncidentes;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.utils.CalculadorDeFechas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioFallasTecnicas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
public class ControladorFallaTecnica {
    private IBuscadorDeTecnicos buscadorDeTecnicos;
    public void recibirFallaTecnica(FallaTecnicaDTO fallaTecnicaDTO) {
        Colaborador reportador = null;
        LocalDateTime fechaYHora;
        Heladera heladera = null;

        Optional<Colaborador> reportadorOptional = RepositorioColaboradores.getInstance().buscarPor(fallaTecnicaDTO.getDoc(), fallaTecnicaDTO.getTipoDoc());
        if (reportadorOptional.isPresent()) reportador = reportadorOptional.get();
        fechaYHora = CalculadorDeFechas.getInstance().stringToLocalDateTime(fallaTecnicaDTO.getFechaYHora());
        Optional<Heladera> heladeraOptional = RepositorioHeladeras.getInstance().buscar(fallaTecnicaDTO.getIdHeladera());
        if (heladeraOptional.isPresent()) heladera = heladeraOptional.get();

        RegistradorDeIncidentes.getInstance().registrarIncidente(Estado.FALLA_TECNICA, heladera, buscadorDeTecnicos);

        FallaTecnica fallaTecnica = CreadorFallaTecnica.getInstance().crearFallaTecnica(heladera, reportador, fallaTecnicaDTO.getDescripcion(), fallaTecnicaDTO.getFoto(), fechaYHora);
        RepositorioFallasTecnicas.getInstance().guardar(fallaTecnica);
    }
}
