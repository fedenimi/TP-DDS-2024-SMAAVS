package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medicion")
public class Medicion {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "temperatura_actual", columnDefinition = "FLOAT(5,2)")
    @Getter private float temperaturaActual;

    // TODO: Converter
    private LocalDateTime tiempoActual;
}
