package ar.edu.utn.frba.dds.modelo.entidades.suscripciones;

import ar.edu.utn.frba.dds.config.ServiceLocator;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.Alerta;
import ar.edu.utn.frba.dds.modelo.entidades.enviadores.Llamador;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.condiciones.CondicionSuscripcionHeladera;
import ar.edu.utn.frba.dds.modelo.entidades.utils.converters.CondicionSuscripcionHeladeraConverter;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import ar.edu.utn.frba.dds.servicios.ServiceTopics;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "topic")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter private Long id;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "topic_id")
    private List<Suscripcion> suscripciones;

    @Column(name = "mensaje", columnDefinition = "TEXT")
    private String mensaje;

    @Convert(converter = CondicionSuscripcionHeladeraConverter.class)
    @Column(name = "condicion_suscripcion_heladera")
    private CondicionSuscripcionHeladera condicionSuscripcionHeladera;
    public void notificarSuscriptores(Heladera heladera) {
        suscripciones.forEach(suscripcion -> {
                if (condicionSuscripcionHeladera.debeEnviar(heladera, suscripcion)){
                    this.enviarAlertaSuscripcion(suscripcion.getSuscriptor(), heladera);
                }
            }
        );
    }

    public void enviarAlertaSuscripcion(Colaborador suscriptor, Heladera heladera) {
        Llamador.getInstance().llamar(suscriptor.getMediosDeContacto(),mensaje, "HELADERA: " + heladera.getId());
        AlertaSuscripcion alertaSuscripcion = ServiceTopics.alertaSuscripcionPara(suscriptor, heladera, this.condicionSuscripcionHeladera);
        suscriptor.guardarAlertaSuscripcion(alertaSuscripcion);

        System.out.println("LLEGUE");
        RepositorioColaboradores repositorioColaboradores = ServiceLocator.instanceOf(RepositorioColaboradores.class);
        repositorioColaboradores.beginTransaction();
        repositorioColaboradores.modificar(suscriptor);
        repositorioColaboradores.commitTransaction();
    }

    public void agregarSuscripcion(Suscripcion suscripcion){
        suscripciones.add(suscripcion);
    }
}
