package ar.edu.utn.frba.dds.dtos;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DonacionDeDineroDTO {
    private String id;
    private String monto;
    private String frecuencia;
}
