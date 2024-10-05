package ar.edu.utn.frba.dds.modelo.entidades.datosPersonas;

import lombok.*;

import javax.persistence.*;
@Setter
@NoArgsConstructor
@Getter
@Entity
@Table(name = "medio_de_contacto")
@Builder
@AllArgsConstructor
public class MedioDeContacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor", columnDefinition = "varchar(255)")
    private String valor;

    @Column(name = "tipo", columnDefinition = "varchar(8)")
    @Enumerated(EnumType.STRING)
    private TipoDeContacto tipo;

    public MedioDeContacto(String valor, TipoDeContacto tipo) {
        this.valor = valor;
        this.tipo = tipo;
    }
}
