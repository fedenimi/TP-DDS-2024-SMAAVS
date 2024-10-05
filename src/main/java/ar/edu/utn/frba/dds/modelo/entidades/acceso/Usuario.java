package ar.edu.utn.frba.dds.modelo.entidades.acceso;

import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", columnDefinition = "VARCHAR(255)")
    private String nombre;

    @Column(name = "contrasenia", columnDefinition = "VARCHAR(16)")
    private String contrasenia;

    @ElementCollection
    @CollectionTable(name = "usuario_permisos", joinColumns= @JoinColumn(name= "usuario_id"))
    @Column(name = "permiso")
    private List<Permiso> permisos;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "colaborador_asociado_id", referencedColumnName = "id")
    private Colaborador colaboradorAsociado;

    public void agregarPermiso(Permiso permiso) {
        permisos.add(permiso);
    }
}
