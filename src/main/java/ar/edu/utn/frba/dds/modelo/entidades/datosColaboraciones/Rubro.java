package ar.edu.utn.frba.dds.modelo.entidades.datosColaboraciones;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Getter
@Entity
@Table(name = "rubro")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rubro {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nombre", columnDefinition = "VARCHAR(255)")
    private String nombre;
}
