package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.entidades.utils.converters.LocalDateConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Entity
@Table(name = "vianda")
public class Vianda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_caducidad")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate fechaCaducidad;

    @Column(name = "comida", columnDefinition = "VARCHAR(50)")
    private String comida;

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    @Getter private Colaborador colaborador;

    @Column(name = "calorias", columnDefinition = "int")
    private Integer calorias;

    @Column(name = "peso", columnDefinition = "int")
    private Integer peso;

    @Column(name = "entregada", columnDefinition = "smallint")
    private boolean entregada;


}
