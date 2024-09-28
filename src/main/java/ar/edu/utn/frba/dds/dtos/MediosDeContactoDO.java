package ar.edu.utn.frba.dds.dtos;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MediosDeContactoDO {
    private String telefono;
    private String email;
    private String whatsapp;
}
