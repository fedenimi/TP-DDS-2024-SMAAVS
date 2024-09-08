package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import ar.edu.utn.frba.dds.modelo.entidades.utils.converters.LocalDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Setter
@Entity
@Table(name = "alerta")
@NoArgsConstructor
@AllArgsConstructor
public class Alerta {
   @Getter
   @Id
   @GeneratedValue
   private Long id;

   @Column(name = "tipo_alerta")
   @Enumerated(EnumType.STRING)
   private Estado tipoAlerta;

   @Column(name = "fecha_y_hora")
   @Convert(converter = LocalDateTimeConverter.class)
   @Getter private LocalDateTime fechaYHora;

   @ManyToOne
   @JoinColumn(name = "heladera_id")
   @Getter private Heladera heladera;
}
