package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import lombok.AllArgsConstructor;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.time.LocalDateTime;

@AllArgsConstructor
public class SensorTemperatura implements IMqttMessageListener {
    private ReceptorSensorTemperatura receptor;
    public Heladera getHeladera() {
        return receptor.getHeladera();
    }
    public LocalDateTime getUltimaConexion() {
        return receptor.getUltimaConexion();
    }
    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        String temperaturaString = mqttMessage.toString();
        Temperatura temp = new Temperatura(Float.parseFloat(temperaturaString));
        receptor.agregarMedicion(temperaturaString);
        if(!receptor.evaluarTemperatura(temp)) {
            Alerta alerta = receptor.crearAlerta(receptor.getHeladera(), Estado.FALLA_TEMPERATURA);
        }
        System.out.println("Message recived from topic " + topic + ": " + mqttMessage.toString());
    }
}