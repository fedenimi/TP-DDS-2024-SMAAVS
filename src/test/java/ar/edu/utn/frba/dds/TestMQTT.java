package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.controladores.ControladorFallaConexion;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.IBuscadorDeTecnicos;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores.ReceptorSensorTemperatura;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.Documento;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.MedioDeContacto;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TipoDeContacto;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.TipoDocumento;
import ar.edu.utn.frba.dds.modelo.entidades.enviadores.Enviador;
import ar.edu.utn.frba.dds.modelo.entidades.enviadores.EnviadorDeMail;
import ar.edu.utn.frba.dds.modelo.entidades.enviadores.Llamador;
import ar.edu.utn.frba.dds.modelo.entidades.localizacion.Punto;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Tecnico;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioAlertas;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioSensoresTemperatura;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores.SensorTemperatura;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestMQTT {
    String topic;
    String content;
    int qos;
    String broker;
    String clientId;
    MemoryPersistence persistence;
    Heladera heladera;
    ReceptorSensorTemperatura receptorTemperatura;
    @Before
    public void initialize() {
        topic = "test";
        content = "Message from MqttPublishSample";
        qos = 2;
        broker = "tcp://broker.hivemq.com:1883";
        clientId = "JavaSample";
        persistence = new MemoryPersistence();
        heladera = new Heladera("123", LocalDateTime.of(2021, 1, 1, 0, 0));
        RepositorioHeladeras.getInstance().agregar(heladera);
        receptorTemperatura = new ReceptorSensorTemperatura(new ArrayList<>(), heladera);
        RepositorioSensoresTemperatura.getInstance().agregar(new SensorTemperatura(receptorTemperatura));
    }

    @Test
    public void recepcionTemperatura() {
        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);

            System.out.println("Connecting to broker: " + broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");

            System.out.println("Build our receptor");
            SensorTemperatura receptor = new SensorTemperatura(receptorTemperatura);

            System.out.println("Now we subscribe to the topic");
            sampleClient.subscribe(topic, receptor);

            System.out.println("Right! We are subscribed");
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }

    @Test
    public void verificarFallasConexion() {
        IBuscadorDeTecnicos buscadorDeTecnicos = mock(IBuscadorDeTecnicos.class);
        ControladorFallaConexion controladorFallaConexion = new ControladorFallaConexion(5, buscadorDeTecnicos);
        when(buscadorDeTecnicos.buscarTecnicoMasCercanoA(ArgumentMatchers.any(Heladera.class)))
                .thenReturn(new Tecnico(
                        "Juan",
                        "Perez",
                        new Documento("123", TipoDocumento.DNI),
                        "201232",
                        new MedioDeContacto("podolskytomi@gmail.com", TipoDeContacto.MAIL),
                        null));
        Llamador.getInstance().setEnviadorDeMail(mock(EnviadorDeMail.class));
        controladorFallaConexion.verificarConexiones();
        Assert.assertEquals(1, RepositorioAlertas.getInstance().buscarTodos().size());
    }

}
