package ar.edu.utn.frba.dds.modelo.entidades.personas;

import ar.edu.utn.frba.dds.modelo.entidades.localizacion.AreaDeCobertura;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.Documento;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.MedioDeContacto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tecnico")
public class Tecnico {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nombre", columnDefinition = "varchar(255)")
    private String nombre;

    @Column(name = "apellido", columnDefinition = "varchar(255)")
    private String apellido;

    @ManyToOne
    @JoinColumn(name = "documento_id")
    private Documento documento;

    @Column(name = "cuil", columnDefinition = "varchar(255)")
    private String cuil;

    @ManyToOne
    @JoinColumn(name = "medio_de_contacto_id")
    private MedioDeContacto medioDeContacto;

    @ManyToOne
    @JoinColumn(name = "area_de_cobertura_id")
    private AreaDeCobertura areaDeCobertura;
}
