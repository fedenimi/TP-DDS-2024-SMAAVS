package ar.edu.utn.frba.dds.modelo.entidades.suscripciones;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.enviadores.Llamador;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.condiciones.CondicionSuscripcionHeladera;
import ar.edu.utn.frba.dds.modelo.entidades.utils.converters.CondicionSuscripcionHeladeraConverter;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "topic")
public class Topic {
    @Id
    @GeneratedValue
    @Getter private Long id;

    @OneToMany
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
                    Llamador.getInstance().llamar(suscripcion.getSuscriptor().getMediosDeContacto(),mensaje, "HELADERA: " + heladera.getId());
                }
            }
        );
    }
}
