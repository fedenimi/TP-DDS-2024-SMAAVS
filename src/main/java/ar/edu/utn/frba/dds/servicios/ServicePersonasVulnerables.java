package ar.edu.utn.frba.dds.servicios;

import ar.edu.utn.frba.dds.dtos.PersonaVulnerableDTO;
import ar.edu.utn.frba.dds.modelo.entidades.personas.PersonaVulnerable;

public class ServicePersonasVulnerables {
    public static PersonaVulnerableDTO toPersonaVulnerableDTO (PersonaVulnerable personaVulnerable) {
        return PersonaVulnerableDTO.builder().
                id(personaVulnerable.getId().toString()).
                nombre(personaVulnerable.getNombre()).
                domicilio(personaVulnerable.getDomicilio()).
                tipo_documento(personaVulnerable.getDocumento().getTipo().toString()).
                num_documento(personaVulnerable.getDocumento().getNumero()).
                build();
    }
}
