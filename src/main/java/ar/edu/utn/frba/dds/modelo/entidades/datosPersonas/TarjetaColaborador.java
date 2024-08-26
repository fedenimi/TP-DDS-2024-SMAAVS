package ar.edu.utn.frba.dds.modelo.entidades.datosPersonas;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tarjeta_colaborador")
public class TarjetaColaborador {
    @Id
    @GeneratedValue
    @Getter
    private String id;

    // TODO: converter
    private LocalDateTime fechaEmision;
}
