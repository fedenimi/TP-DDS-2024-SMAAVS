package ar.edu.utn.frba.dds.modelo.entidades.localizacion;

import javax.persistence.*;

@Entity
@Table(name = "provincia_o_estado")
public class ProvinciaOEstado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", columnDefinition = "VARCHAR(255)")
    private String nombre;
}
