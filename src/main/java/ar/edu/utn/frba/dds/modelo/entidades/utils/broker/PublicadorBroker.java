package ar.edu.utn.frba.dds.modelo.entidades.utils.broker;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class PublicadorBroker {

    private MqttClient client;
    private static PublicadorBroker instance = null;
    public static PublicadorBroker getInstance() {
        if(instance == null)
            instance = new PublicadorBroker();
        return instance;
    }

    public void publicar(String topic, String mensaje) throws MqttException {
        this.conectarse();
        try {
            MqttMessage message = new MqttMessage(mensaje.getBytes());
            message.setQos(2);
            client.publish(topic, message);
            System.out.println("Message published: " + mensaje);
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }

    private void conectarse() {
        try {
            String broker = "tcp://broker.hivemq.com:1883"; // Cambia esto por tu broker si es diferente
            String clientId = "JavaSamplePublisher";
            MemoryPersistence persistence = new MemoryPersistence();
            client = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: " + broker);
            client.connect(connOpts);
            System.out.println("Connected");
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }

}
