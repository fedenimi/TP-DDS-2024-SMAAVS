package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "donacion_de_viandas")
public class DonacionDeViandas{
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    @Getter
    @Setter
    protected Colaborador colaborador;

    @Column(name = "fecha")
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate fecha;
}

