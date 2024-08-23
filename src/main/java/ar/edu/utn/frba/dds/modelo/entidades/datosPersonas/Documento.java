package ar.edu.utn.frba.dds.modelo.entidades.datosPersonas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "documento")
public class Documento {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "numero", columnDefinition = "varchar(20)")
    private String numero;

    @Column(name = "tipo_documento", columnDefinition = "varchar(10)")
    @Enumerated(EnumType.STRING)
    @Getter private TipoDocumento tipo;
}
