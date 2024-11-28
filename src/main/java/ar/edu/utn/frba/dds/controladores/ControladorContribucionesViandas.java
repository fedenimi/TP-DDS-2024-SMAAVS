package ar.edu.utn.frba.dds.controladores;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.dtos.AperturaDTO;
import ar.edu.utn.frba.dds.dtos.DonacionViandasDTO;
import ar.edu.utn.frba.dds.dtos.SolicitudAperturaHeladeraDTO;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DistribucionDeViandas;
import ar.edu.utn.frba.dds.modelo.entidades.colaboraciones.DonacionDeViandas;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Apertura;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.SolicitudApertura;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.FormaColaboracion;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TipoDocumento;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.utils.CalculadorDeFechas;
import ar.edu.utn.frba.dds.modelo.entidades.utils.broker.PublicadorBroker;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioAperturas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ControladorContribucionesViandas {
    private static ControladorContribucionesViandas instance = null;
    public static ControladorContribucionesViandas getInstance() {
        if(instance == null)
            instance = new ControladorContribucionesViandas();
        return instance;
    }
    // TODO: no estoy seguro pero por ahí dsps ver esto
    /*
    public void solicitarAperturaDeHeladera(SolicitudAperturaHeladeraDTO solicitudAperturaHeladeraDTO) throws Exception {
        Optional<Colaborador> optionalColaborador = RepositorioColaboradores.getInstance().
            buscarPor(solicitudAperturaHeladeraDTO.getTipoDoc(), solicitudAperturaHeladeraDTO.getDoc());
        if (!optionalColaborador.isPresent()) throw new Exception("No se encontro el colaborador");
        Colaborador colaborador = optionalColaborador.get();
        if(!this.puedeDonar(colaborador)) throw new Exception("El colaborador no puede donar viandas");
        Optional<Heladera> optionalHeladera = RepositorioHeladeras.getInstance().buscar(Long.parseLong(solicitudAperturaHeladeraDTO.getIdHeladera()));
        if (!optionalHeladera.isPresent()) throw new Exception("No se encontro la heladera");
        Heladera heladera = optionalHeladera.get();
        this.solicitarAperturaDeHeladera(heladera, colaborador);
    }

     */
    /*
    private void solicitarAperturaDeHeladera(Heladera heladera, Colaborador colaborador) throws MqttException {
        SolicitudApertura solicitudApertura = new SolicitudApertura(1L,colaborador.getTarjeta(), LocalDateTime.now());
        heladera.agregarSolicitudApertura(solicitudApertura);
        PublicadorBroker.getInstance().publicar("apertura", heladera.getId().toString() + " " + colaborador.getTarjeta().getId());
    }

     */
    public boolean puedeDonar(Colaborador colaborador) {
        return colaborador.getFormasDeColaborar().stream().anyMatch(formaDeColaborar -> formaDeColaborar.equals(FormaColaboracion.DONACION_VIANDAS));
    }

    public void donacionViandas(AperturaDTO aperturaDTO, DonacionViandasDTO donacionViandasDTO) throws Exception {
        this.abrirHeladera(aperturaDTO);
        this.donarViandas(donacionViandasDTO);
    }
    public void donarViandas(DonacionViandasDTO donacionViandasDTO) {
        //new DonacionDeViandas();
    }
    // TODO: y esto tmb

    public void abrirHeladera(AperturaDTO aperturaDTO) throws Exception {
        Optional<Heladera> optionalHeladera = ServiceLocator.instanceOf(RepositorioHeladeras.class).buscar(Long.parseLong(aperturaDTO.getIdHeladera()));
        if (!optionalHeladera.isPresent()) throw new Exception("No se encontro la heladera");
        Heladera heladera = optionalHeladera.get();
        Optional<Colaborador> optionalColaborador = ServiceLocator.instanceOf(RepositorioColaboradores.class).
                buscarPor(aperturaDTO.getDoc(), TipoDocumento.valueOf(aperturaDTO.getTipoDoc()));
        if (!optionalColaborador.isPresent()) throw new Exception("No se encontro el colaborador");
        Colaborador colaborador = optionalColaborador.get();
        Apertura apertura = new Apertura(colaborador.getTarjetaColaborador(),
                LocalDateTime.now(),
                heladera.buscarSolicitudAperturaPor(colaborador.getTarjetaColaborador()));

        List<DonacionDeViandas> donacionesDeViandas = colaborador.getPuntuables()
                .stream()
                .filter(puntuable -> puntuable instanceof DonacionDeViandas)
                .map(puntuable -> (DonacionDeViandas) puntuable)
                .collect(Collectors.toList());
        List<DistribucionDeViandas> distribucionesDeViandas = colaborador.getPuntuables()
                .stream()
                .filter(puntuable -> puntuable instanceof DistribucionDeViandas)
                .map(puntuable -> (DistribucionDeViandas) puntuable)
                .collect(Collectors.toList());

        Optional<DonacionDeViandas> optionalDonacionDeViandas = donacionesDeViandas.stream().filter(d -> d.getApertura() == null && heladera.getSolicitudAperturas().contains(d.getSolicitudApertura())).findFirst();
        if (optionalDonacionDeViandas.isPresent()) {
            DonacionDeViandas donacionDeViandas = optionalDonacionDeViandas.get();
            donacionDeViandas.setApertura(apertura);
        }
        Optional<DistribucionDeViandas> optionalDistribucionDeViandasOrigen = distribucionesDeViandas.stream().filter(d -> d.getAperturaOrigen() == null && heladera.getSolicitudAperturas().contains(d.getSolicitudAperturaOrigen())).findFirst();
        if (optionalDistribucionDeViandasOrigen.isPresent()) {
            DistribucionDeViandas distribucionDeViandas = optionalDistribucionDeViandasOrigen.get();
            distribucionDeViandas.setAperturaOrigen(apertura);
        }
        Optional<DistribucionDeViandas> optionalDistribucionDeViandasDestino = distribucionesDeViandas.stream().filter(d -> d.getAperturaDestino() == null && heladera.getSolicitudAperturas().contains(d.getSolicitudAperturaDestino())).findFirst();
        if (optionalDistribucionDeViandasDestino.isPresent()) {
            if(optionalDistribucionDeViandasDestino.get().getAperturaOrigen() != null) {
                System.out.println("FRAA");
                DistribucionDeViandas distribucionDeViandas = optionalDistribucionDeViandasDestino.get();
                distribucionDeViandas.setAperturaDestino(apertura);
            }
        }
        ServiceLocator.instanceOf(RepositorioAperturas.class).guardar(apertura);
        heladera.agregarApertura(apertura);
        ServiceLocator.instanceOf(RepositorioHeladeras.class).modificar(heladera);
    }
}


