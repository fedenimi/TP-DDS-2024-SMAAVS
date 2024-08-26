package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.incidentes.sensores;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "medicion")
public class Medicion {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "temperatura", columnDefinition = "FLOAT(5,2)")
    private float temperatura;

    //TODO: converter
    private LocalDateTime fechaYHora;
}
