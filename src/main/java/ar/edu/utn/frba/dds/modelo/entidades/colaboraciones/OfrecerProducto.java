package ar.edu.utn.frba.dds.modelo.entidades.colaboraciones;

import ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones.Oferta;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ofrecer_producto")
@Builder
public class OfrecerProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Oferta oferta;

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    private Colaborador colaborador;

}
