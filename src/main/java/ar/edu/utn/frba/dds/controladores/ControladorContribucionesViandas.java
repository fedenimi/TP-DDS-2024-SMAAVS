package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.dtos.AperturaDTO;
import ar.edu.utn.frba.dds.dtos.DonacionViandasDTO;
import ar.edu.utn.frba.dds.dtos.SolicitudAperturaHeladeraDTO;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DonacionDeViandas;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Apertura;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.SolicitudApertura;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.FormaColaboracion;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.utils.CalculadorDeFechas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;

import java.time.LocalDateTime;
import java.util.Optional;

public class ControladorContribucionesViandas {
    public void solicitarAperturaDeHeladera(SolicitudAperturaHeladeraDTO solicitudAperturaHeladeraDTO) throws Exception {
        Optional<Colaborador> optionalColaborador = RepositorioColaboradores.getInstance().
            buscarPor(solicitudAperturaHeladeraDTO.getTipoDoc(), solicitudAperturaHeladeraDTO.getDoc());
        if (!optionalColaborador.isPresent()) throw new Exception("No se encontro el colaborador");
        Colaborador colaborador = optionalColaborador.get();
        if(!this.puedeDonar(colaborador)) throw new Exception("El colaborador no puede donar viandas");
        Optional<Heladera> optionalHeladera = RepositorioHeladeras.getInstance().buscar(solicitudAperturaHeladeraDTO.getIdHeladera());
        if (!optionalHeladera.isPresent()) throw new Exception("No se encontro la heladera");
        Heladera heladera = optionalHeladera.get();
        this.solicitarAperturaDeHeladera(heladera, colaborador);
    }
    private void solicitarAperturaDeHeladera(Heladera heladera, Colaborador colaborador) {
        SolicitudApertura solicitudApertura = new SolicitudApertura(colaborador.getTarjeta(), LocalDateTime.now());
        heladera.agregarSolicitudApertura(solicitudApertura);
    }
    public boolean puedeDonar(Colaborador colaborador) {
        return colaborador.getFormasDeColaborar().stream().anyMatch(formaDeColaborar -> formaDeColaborar.equals(FormaColaboracion.DONACION_VIANDAS));
    }

    public void donacionViandas(AperturaDTO aperturaDTO, DonacionViandasDTO donacionViandasDTO) {
        this.abrirHeladera(aperturaDTO);
        this.donarViandas(donacionViandasDTO);
    }
    public void donarViandas(DonacionViandasDTO donacionViandasDTO) {
     new DonacionDeViandas()
    }

    public void abrirHeladera(AperturaDTO aperturaDTO) throws Exception {
        Optional<Heladera> optionalHeladera = RepositorioHeladeras.getInstance().buscar(aperturaDTO.getIdHeladera());
        if (!optionalHeladera.isPresent()) throw new Exception("No se encontro la heladera");
        Heladera heladera = optionalHeladera.get();

        Optional<Colaborador> optionalColaborador = RepositorioColaboradores.getInstance().
                buscarPor(aperturaDTO.getTipoDoc(), aperturaDTO.getDoc());
        if (!optionalColaborador.isPresent()) throw new Exception("No se encontro el colaborador");
        Colaborador colaborador = optionalColaborador.get();

        heladera.agregarApertura(new Apertura(colaborador.getTarjeta(),
                CalculadorDeFechas.getInstance().stringToLocalDateTime(aperturaDTO.getFechaApertura()),
                heladera.buscarSolicitudAperturaPor(colaborador.getTarjeta(),
                CalculadorDeFechas.getInstance().stringToLocalDateTime(aperturaDTO.getFechaSolicitud()))));
    }
}
