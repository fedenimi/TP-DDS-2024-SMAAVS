package ar.edu.utn.frba.dds.modelo.entidades.localizacion;

import javax.persistence.*;

@Entity
@Table(name = "ciudad")
public class Ciudad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", columnDefinition = "VARCHAR(255)")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "partido_o_departamento_id")
    private PartidoODepartamento partidoODepartamento;
}
