package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "alerta")
@NoArgsConstructor
@AllArgsConstructor
public class Alerta {
   @Id
   @GeneratedValue
   private Long id;

   @Column(name = "tipo_alerta")
   @Enumerated(EnumType.STRING)
   private Estado tipoAlerta;

   //TODO: converter
   @Getter private LocalDateTime fechaYHora;

   @ManyToOne
   @JoinColumn(name = "heladera_id")
   @Getter private Heladera heladera;
}
