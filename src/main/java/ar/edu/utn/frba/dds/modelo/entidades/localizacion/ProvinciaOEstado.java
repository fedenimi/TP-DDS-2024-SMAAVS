package ar.edu.utn.frba.dds.modelo.entidades.localizacion;

import javax.persistence.*;

@Entity
@Table(name = "provincia_o_estado")
public class ProvinciaOEstado {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "nombre", columnDefinition = "VARCHAR")
    private String nombre;
}
