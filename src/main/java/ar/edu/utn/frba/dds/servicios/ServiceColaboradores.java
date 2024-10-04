package ar.edu.utn.frba.dds.servicios;

import ar.edu.utn.frba.dds.dtos.ColaboradorDTO;
import ar.edu.utn.frba.dds.dtos.FormasDeColaborarDO;
import ar.edu.utn.frba.dds.dtos.MediosDeContactoDO;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.*;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.formulario.Formulario;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.formulario.FormularioRespondido;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.formulario.Pregunta;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.formulario.Respuesta;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;

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
        public static void setearMediosDeContacto(Colaborador colaborador, MediosDeContactoDO mediosDeContactoDO) {
        if(mediosDeContactoDO.getTelefono() != null && !colaborador.tiene(TipoDeContacto.TELEFONO)) {
            colaborador.agregarMedioDeContacto(new MedioDeContacto(mediosDeContactoDO.getTelefono(), TipoDeContacto.TELEFONO));
        }
        else if(!colaborador.getValorDeContacto(TipoDeContacto.TELEFONO).equals(mediosDeContactoDO.getTelefono())) {
            colaborador.quitarContactoDeTipo(TipoDeContacto.TELEFONO);
            colaborador.agregarMedioDeContacto(new MedioDeContacto(mediosDeContactoDO.getTelefono(), TipoDeContacto.TELEFONO));
        }
        else if(mediosDeContactoDO.getEmail() != null && !colaborador.tiene(TipoDeContacto.MAIL)) {
            colaborador.agregarMedioDeContacto(new MedioDeContacto(mediosDeContactoDO.getEmail(), TipoDeContacto.MAIL));
        }
        else if(!colaborador.getValorDeContacto(TipoDeContacto.MAIL).equals(mediosDeContactoDO.getEmail())) {
            colaborador.quitarContactoDeTipo(TipoDeContacto.MAIL);
            colaborador.agregarMedioDeContacto(new MedioDeContacto(mediosDeContactoDO.getEmail(), TipoDeContacto.MAIL));
        }
        else if(mediosDeContactoDO.getWhatsapp() != null && !colaborador.tiene(TipoDeContacto.WHATSAPP)) {
            colaborador.agregarMedioDeContacto(new MedioDeContacto(mediosDeContactoDO.getWhatsapp(), TipoDeContacto.WHATSAPP));
        }
        else if(!colaborador.getValorDeContacto(TipoDeContacto.WHATSAPP).equals(mediosDeContactoDO.getWhatsapp())) {
            colaborador.quitarContactoDeTipo(TipoDeContacto.WHATSAPP);
            colaborador.agregarMedioDeContacto(new MedioDeContacto(mediosDeContactoDO.getWhatsapp(), TipoDeContacto.WHATSAPP));
        }
        }

        public static void setearFormularioRespondido(Colaborador colaborador, Context context) {
            //Setear el formulario
            Formulario formulario = new Formulario();
            List<Respuesta> respuestas = new ArrayList<>();
            if(context.formParam("fechaDeNacimiento") != null) {
                Pregunta preguntaFechaNac = new Pregunta("Fecha de nacimiento");
                formulario.agregarPregunta(preguntaFechaNac);
                Respuesta respuestaFechaNac = Respuesta.builder().pregunta(preguntaFechaNac).respuesta(context.formParam("fechaDeNacimiento")).build();
                respuestas.add(respuestaFechaNac);
            }
            if(context.formParam("direccion") != null) {
                Pregunta preguntaDireccion = new Pregunta("Direccion");
                formulario.agregarPregunta(preguntaDireccion);
                Respuesta respuestaDireccion = Respuesta.builder().pregunta(preguntaDireccion).respuesta(context.formParam("direccion")).build();
                respuestas.add(respuestaDireccion);
            }
            FormularioRespondido formularioRespondido = FormularioRespondido.builder().
                    formulario(formulario).
                    respuestas(respuestas).
                    build();
            colaborador.setFormularioRespondido(formularioRespondido);
        }

        public static Colaborador crearColaboradorHumano(Context context) {
            Colaborador colaborador = new Colaborador();
            // Setear valores básicos
            colaborador.setNombre(context.formParam("nombre"));
            colaborador.setApellido(context.formParam("apellido"));
            //colaborador.setDocumento(Documento.builder().numero(context.formParam("documento")).tipo(TipoDocumento.valueOf(context.formParam("tipoDocumento"))).build());
            colaborador.setTipoDeColaborador(TipoDeColaborador.HUMANA);
            if(context.formParam("telefono") != null) {
                colaborador.agregarMedioDeContacto(MedioDeContacto.builder().tipo(TipoDeContacto.TELEFONO).valor(context.formParam("telefono")).build());
            }
            if(context.formParam("email") != null) {
                colaborador.agregarMedioDeContacto(MedioDeContacto.builder().tipo(TipoDeContacto.MAIL).valor(context.formParam("email")).build());
            }
            if(context.formParam("whatsapp") != null) {
                colaborador.agregarMedioDeContacto(MedioDeContacto.builder().tipo(TipoDeContacto.WHATSAPP).valor(context.formParam("whatsapp")).build());
            }
            if(context.formParam("donarDinero") != null) {
                colaborador.agregarFormaDeColaborar(FormaColaboracion.DONACION_DINERO);
            }
            if(context.formParam("donarViandas") != null) {
                colaborador.agregarFormaDeColaborar(FormaColaboracion.DONACION_VIANDAS);
            }
            if(context.formParam("distribuirViandas") != null) {
                colaborador.agregarFormaDeColaborar(FormaColaboracion.DISTRIBUCION_VIANDAS);
            }

            ServiceColaboradores.setearFormularioRespondido(colaborador, context);
            return colaborador;
        }

        public static Colaborador crearColaboradorJuridico(Context context) {
        return null;
        }
}
