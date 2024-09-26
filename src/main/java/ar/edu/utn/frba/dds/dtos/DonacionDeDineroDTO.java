package ar.edu.utn.frba.dds.dtos;

import lombok.Builder;

@Builder
public class DonacionDeDineroDTO {
    private String id;
    private String monto;
    private String frecuencia;
}
