package ar.edu.utn.frba.dds.servicios;

import ar.edu.utn.frba.dds.dtos.ColaboradorDTO;
import ar.edu.utn.frba.dds.dtos.FormasDeColaborarDO;
import ar.edu.utn.frba.dds.dtos.MediosDeContactoDO;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.FormaColaboracion;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.MedioDeContacto;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;

public class ServiceColaboradores {
    public static ColaboradorDTO toColaboradorDTO(Colaborador colaborador) {

        return ColaboradorDTO.builder().
                id(String.valueOf(colaborador.getId())).
                nombre(colaborador.getNombre()).
                puntosDisponibles(String.valueOf(colaborador.getPuntosDisponibles())).
                tipoColaborador(colaborador.getTipoDeColaborador().toString()).
                formasDeColaborar(colaborador.getFormasDeColaborar().stream().map(FormaColaboracion::toString).toList()).
                build();

    }

    public static void setearFormasDeColaborar(Colaborador colaborador, FormasDeColaborarDO formasDeColaborarDO) {
        if (formasDeColaborarDO.getDonarDinero() != null && !colaborador.getFormasDeColaborar().contains(FormaColaboracion.DONACION_DINERO)) {
            colaborador.getFormasDeColaborar().add(FormaColaboracion.DONACION_DINERO);
        } else if (formasDeColaborarDO.getDonarViandas() != null && !colaborador.getFormasDeColaborar().contains(FormaColaboracion.DONACION_VIANDAS)) {
            colaborador.getFormasDeColaborar().add(FormaColaboracion.DONACION_VIANDAS);
        } else if (formasDeColaborarDO.getDistribuirViandas() != null && !colaborador.getFormasDeColaborar().contains(FormaColaboracion.DISTRIBUCION_VIANDAS)) {
            colaborador.getFormasDeColaborar().add(FormaColaboracion.DISTRIBUCION_VIANDAS);
        } else if (formasDeColaborarDO.getAdministrarHeladeras() != null && !colaborador.getFormasDeColaborar().contains(FormaColaboracion.HACERSE_CARGO_DE_HELADERA)) {
            colaborador.getFormasDeColaborar().add(FormaColaboracion.HACERSE_CARGO_DE_HELADERA);
        }
    }
        /*
        public static void setearMediosDeContacto(Colaborador colaborador, MediosDeContactoDO mediosDeContactoDO) {
        if(mediosDeContactoDO.getTelefono())

            MedioDeContacto
        }

         */


}
