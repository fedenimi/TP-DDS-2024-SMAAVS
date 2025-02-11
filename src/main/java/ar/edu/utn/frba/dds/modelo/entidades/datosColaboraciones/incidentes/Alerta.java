package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Heladera;
import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.infoHeladera.Estado;
import ar.edu.utn.frba.dds.modelo.entidades.utils.converters.LocalDateTimeConverter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "alerta")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alerta {
   @Getter
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "tipo_alerta")
   @Enumerated(EnumType.STRING)
   private Estado tipoAlerta;

   @Column(name = "fecha_y_hora")
   @Convert(converter = LocalDateTimeConverter.class)
   @Getter private LocalDateTime fechaYHora;

   @ManyToOne(cascade = {CascadeType.REMOVE})
   @JoinColumn(name = "heladera_id")
   @Getter private Heladera heladera;
}
