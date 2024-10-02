package ar.edu.utn.frba.dds.servicios;

import ar.edu.utn.frba.dds.dtos.PersonaVulnerableDTO;
import ar.edu.utn.frba.dds.modelo.entidades.personas.PersonaVulnerable;
import ar.edu.utn.frba.dds.modelo.entidades.suscripciones.Suscripcion;

public class ServicePersonasVulnerables {
    public static PersonaVulnerableDTO toPersonaVulnerableDTO (PersonaVulnerable personaVulnerable) {
        return PersonaVulnerableDTO.builder().
                id(personaVulnerable.getId().toString()).
                nombre(personaVulnerable.getNombre()).
                domicilio(personaVulnerable.getDomicilio().getDireccion()).
                tipo_documento(personaVulnerable.getDocumento().getTipo().toString()).
                num_documento(personaVulnerable.getDocumento().getNumero()).
                fecha_de_nacimiento(personaVulnerable.getFechaDeNacimiento().toString()).
                build();
    }
}