package ar.edu.utn.frba.dds.modelo.entidades.localizacion;

import javax.persistence.*;

@Entity
@Table(name = "partido_o_departamento")
public class PartidoODepartamento {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "nombre", columnDefinition = "VARCHAR(255)")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private ProvinciaOEstado provincia;
}
