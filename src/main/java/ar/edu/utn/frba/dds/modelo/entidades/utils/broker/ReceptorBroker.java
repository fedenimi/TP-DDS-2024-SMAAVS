package ar.edu.utn.frba.dds.modelo.entidades.utils.broker;

import ar.edu.utn.frba.dds.controladores.ControladorContribucionesViandas;
import ar.edu.utn.frba.dds.controladores.ControladorMovimiento;
import ar.edu.utn.frba.dds.dtos.AperturaDTO;
import ar.edu.utn.frba.dds.dtos.FraudeDTO;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores.ReceptorSensorTemperatura;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores.Temperatura;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioReceptoresTemperatura;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.StringTokenizer;

public class ReceptorBroker implements IMqttMessageListener {
    private static ReceptorBroker instance = null;
    public static ReceptorBroker getInstance() {
        if(instance == null)
            instance = new ReceptorBroker();
        return instance;
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        String mensajeString = mqttMessage.toString();
        StringTokenizer messageTokenizer = new StringTokenizer(mensajeString, " ");
        String idHeladera = messageTokenizer.nextToken();
        switch(topic) {
            case "temperatura":
                String temperatura = messageTokenizer.nextToken();
                ReceptorSensorTemperatura receptor = RepositorioReceptoresTemperatura.getInstance().buscar(Long.parseLong(idHeladera)).get();
                receptor.evaluarTemperatura(new Temperatura(Double.parseDouble(temperatura)));
                break;
            case "fraude":
                String fechaYHora = messageTokenizer.nextToken();
                FraudeDTO fraudeDTO = new FraudeDTO(fechaYHora, idHeladera);
                ControladorMovimiento.getInstance().recibirFraude(fraudeDTO);
                break;
            case "apertura":
                String tipoDoc = messageTokenizer.nextToken();
                String doc = messageTokenizer.nextToken();
                String fechaSolicitud = messageTokenizer.nextToken();
                String fechaApertura = messageTokenizer.nextToken();
                ControladorContribucionesViandas.getInstance().abrirHeladera(new AperturaDTO(tipoDoc, doc, idHeladera, fechaSolicitud, fechaApertura));
                break;
            default:
                System.out.println("No se reconoce el topic");
                break;
        }
    }
}
