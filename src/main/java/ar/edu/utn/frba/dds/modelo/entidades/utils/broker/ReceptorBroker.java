package ar.edu.utn.frba.dds.modelo.entidades.utils.broker;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.controladores.ControladorContribucionesViandas;
import ar.edu.utn.frba.dds.controladores.ControladorMovimiento;
import ar.edu.utn.frba.dds.dtos.AperturaDTO;
import ar.edu.utn.frba.dds.dtos.FraudeDTO;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores.ReceptorSensorTemperatura;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores.Temperatura;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioHeladeras;
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
        StringTokenizer messageTokenizer = new StringTokenizer(mensajeString, "/");
        String opcode = messageTokenizer.nextToken();
        String idHeladera = messageTokenizer.nextToken();
        System.out.println("Mensaje recibido: " + mqttMessage);
        switch(opcode) {
            case "temperatura":
                String temperatura = messageTokenizer.nextToken();
                ReceptorSensorTemperatura receptor = ReceptorSensorTemperatura.builder().
                        heladera(ServiceLocator.instanceOf(RepositorioHeladeras.class).buscar(Long.parseLong(idHeladera)).get()).build();
                receptor.evaluarTemperatura(new Temperatura(Double.parseDouble(temperatura)));
                break;
            case "fraude":
                FraudeDTO fraudeDTO = new FraudeDTO(idHeladera);
                ControladorMovimiento.getInstance().recibirFraude(fraudeDTO);
                break;
            case "apertura":
                String tipoDoc = messageTokenizer.nextToken();
                String doc = messageTokenizer.nextToken();
                String fechaSolicitud = messageTokenizer.nextToken();
                System.out.println(fechaSolicitud);
                ControladorContribucionesViandas.getInstance().abrirHeladera(new AperturaDTO(tipoDoc, doc, idHeladera, fechaSolicitud));
                break;
            default:
                System.out.println("No se reconoce el topic");
                break;
        }
    }
}
