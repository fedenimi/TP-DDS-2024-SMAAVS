package ar.edu.utn.frba.dds.servicios;

import ar.edu.utn.frba.dds.dtos.ColaboradorDTO;
import ar.edu.utn.frba.dds.dtos.FormasDeColaborarDO;
import ar.edu.utn.frba.dds.dtos.MediosDeContactoDO;
import ar.edu.utn.frba.dds.modelo.entidades.acceso.Permiso;
import ar.edu.utn.frba.dds.modelo.entidades.acceso.Usuario;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.*;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.formulario.Formulario;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.formulario.FormularioRespondido;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.formulario.Pregunta;
import ar.edu.utn.frba.dds.modelo.entidades.datosPersonas.formulario.Respuesta;
import ar.edu.utn.frba.dds.modelo.entidades.personas.Colaborador;
import ar.edu.utn.frba.dds.modelo.repositorios.RepositorioColaboradores;
import io.javalin.http.Context;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ServiceColaboradores {
    public static ColaboradorDTO toColaboradorDTO(Colaborador colaborador) {

        ColaboradorDTO colaboradorDTO = ColaboradorDTO.builder().
                id(String.valueOf(colaborador.getId())).
                nombre(colaborador.getNombre()).
                puntosDisponibles(String.valueOf(colaborador.getPuntosDisponibles())).
                tipoColaborador(colaborador.getTipoDeColaborador().toString()).
                formasDeColaborar(colaborador.getFormasDeColaborar().stream().map(FormaColaboracion::toString).toList()).build();
        if (colaborador.getValorDeContacto(TipoDeContacto.MAIL).isPresent()) {
            colaboradorDTO.setMail(colaborador.getValorDeContacto(TipoDeContacto.MAIL).get().getValor());
        }
        if (colaborador.getValorDeContacto(TipoDeContacto.TELEFONO).isPresent()) {
            colaboradorDTO.setTelefono(colaborador.getValorDeContacto(TipoDeContacto.TELEFONO).get().getValor());
        }
        if (colaborador.getValorDeContacto(TipoDeContacto.WHATSAPP).isPresent()) {
            colaboradorDTO.setWhatsapp(colaborador.getValorDeContacto(TipoDeContacto.WHATSAPP).get().getValor());
        }
        if (colaborador.getTipoDeColaborador().equals(TipoDeColaborador.JURIDICA)) {
            colaboradorDTO.setJuridica("true");
        }
        return colaboradorDTO;
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
        if(mediosDeContactoDO.getTelefono() != "") {
            if (!colaborador.tiene(TipoDeContacto.TELEFONO)) {
                colaborador.agregarMedioDeContacto(new MedioDeContacto(mediosDeContactoDO.getTelefono(), TipoDeContacto.TELEFONO));
            } else if (!colaborador.getValorDeContacto(TipoDeContacto.TELEFONO).equals(mediosDeContactoDO.getTelefono())) {
                colaborador.quitarContactoDeTipo(TipoDeContacto.TELEFONO);
                colaborador.agregarMedioDeContacto(new MedioDeContacto(mediosDeContactoDO.getTelefono(), TipoDeContacto.TELEFONO));
            }
        }
        if(mediosDeContactoDO.getEmail() != "") {
        if (!colaborador.tiene(TipoDeContacto.MAIL)) {
                colaborador.agregarMedioDeContacto(new MedioDeContacto(mediosDeContactoDO.getEmail(), TipoDeContacto.MAIL));
            } else if (!colaborador.getValorDeContacto(TipoDeContacto.MAIL).equals(mediosDeContactoDO.getEmail())) {
                colaborador.quitarContactoDeTipo(TipoDeContacto.MAIL);
                colaborador.agregarMedioDeContacto(new MedioDeContacto(mediosDeContactoDO.getEmail(), TipoDeContacto.MAIL));
            }
        }
        if(mediosDeContactoDO.getWhatsapp() != "") {
            if (mediosDeContactoDO.getWhatsapp() != "" && !colaborador.tiene(TipoDeContacto.WHATSAPP)) {
                colaborador.agregarMedioDeContacto(new MedioDeContacto(mediosDeContactoDO.getWhatsapp(), TipoDeContacto.WHATSAPP));
            } else if (!colaborador.getValorDeContacto(TipoDeContacto.WHATSAPP).equals(mediosDeContactoDO.getWhatsapp())) {
                colaborador.quitarContactoDeTipo(TipoDeContacto.WHATSAPP);
                colaborador.agregarMedioDeContacto(new MedioDeContacto(mediosDeContactoDO.getWhatsapp(), TipoDeContacto.WHATSAPP));
            }
        }
        }


        public static Colaborador crearColaboradorHumano(Context context, Usuario usuario, RepositorioColaboradores repositorioColaboradores) {
            Colaborador colaborador = new Colaborador();
            // Setear valores básicos
            colaborador.setNombre(context.formParam("nombre"));
            colaborador.setApellido(context.formParam("apellido"));
            colaborador.setDocumento(Documento.builder().numero(context.formParam("nro-documento")).tipo(TipoDocumento.valueOf(context.formParam("tipo-documento"))).build());
            colaborador.setTipoDeColaborador(TipoDeColaborador.HUMANA);
            colaborador.setPuntosCanjeados(0D);
            colaborador.setPuntosDisponibles(0D);
            colaborador.setPuntuables(new ArrayList<>());
            colaborador.setSuscripciones(new ArrayList<>());
            colaborador.setAlertaSuscripciones(new ArrayList<>());
            asignarTarjetaA(colaborador, repositorioColaboradores);

            if(context.formParam("telefono") != null) {
                colaborador.agregarMedioDeContacto(MedioDeContacto.builder().tipo(TipoDeContacto.TELEFONO).valor(context.formParam("telefono")).build());
            }
            if(context.formParam("mail") != null) {
                colaborador.agregarMedioDeContacto(MedioDeContacto.builder().tipo(TipoDeContacto.MAIL).valor(context.formParam("mail")).build());
            }
            if(context.formParam("whatsapp") != null) {
                colaborador.agregarMedioDeContacto(MedioDeContacto.builder().tipo(TipoDeContacto.WHATSAPP).valor(context.formParam("whatsapp")).build());
            }
            if(context.formParam("donar-dinero") != null) {
                colaborador.agregarFormaDeColaborar(FormaColaboracion.DONACION_DINERO);
                usuario.agregarPermiso(Permiso.DONAR_DINERO);
            }
            if(context.formParam("donar-viandas") != null) {
                colaborador.agregarFormaDeColaborar(FormaColaboracion.DONACION_VIANDAS);
                usuario.agregarPermiso(Permiso.DONAR_VIANDAS);
            }
            if(context.formParam("distribuir-viandas") != null) {
                colaborador.agregarFormaDeColaborar(FormaColaboracion.DISTRIBUCION_VIANDAS);
                usuario.agregarPermiso(Permiso.DISTRIBUIR_VIANDAS);
            }

            ServiceColaboradores.setearFormularioRespondidoHumana(colaborador, context);
            return colaborador;
        }

    public static void setearFormularioRespondidoHumana(Colaborador colaborador, Context context) {
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

        public static Colaborador crearColaboradorJuridico(Context context, Usuario usuario) {
        Colaborador colaborador = new Colaborador();
        colaborador.setTipoDeColaborador(TipoDeColaborador.JURIDICA);
        colaborador.setPuntosDisponibles(0D);
        colaborador.setPuntosCanjeados(0D);
        colaborador.setPuntuables(new ArrayList<>());
        colaborador.setOfrecerProductos(new ArrayList<>());
        if(context.formParam("telefono") != null) {
            colaborador.agregarMedioDeContacto(MedioDeContacto.builder().tipo(TipoDeContacto.TELEFONO).valor(context.formParam("telefono")).build());
        }
        if(context.formParam("mail") != null) {
            colaborador.agregarMedioDeContacto(MedioDeContacto.builder().tipo(TipoDeContacto.MAIL).valor(context.formParam("email")).build());
        }
        if(context.formParam("whatsapp") != null) {
            colaborador.agregarMedioDeContacto(MedioDeContacto.builder().tipo(TipoDeContacto.WHATSAPP).valor(context.formParam("whatsapp")).build());
        }
        if(context.formParam("donar-dinero") != null) {
            colaborador.agregarFormaDeColaborar(FormaColaboracion.DONACION_DINERO);
            usuario.agregarPermiso(Permiso.DONAR_DINERO);
        }
        if(context.formParam("administrar-heladeras") != null) {
            colaborador.agregarFormaDeColaborar(FormaColaboracion.DONACION_VIANDAS);
            usuario.agregarPermiso(Permiso.ADMINISTRAR_HELADERA);
        }
        ServiceColaboradores.setearFormularioRespondidoJuridica(colaborador, context);

        return colaborador;
}

public static void setearFormularioRespondidoJuridica(Colaborador colaborador, Context context) {
        //Setear el formulario
        Formulario formulario = new Formulario();
        List<Respuesta> respuestas = new ArrayList<>();

            Pregunta preguntaRazonSocial = new Pregunta("Razon social");
            formulario.agregarPregunta(preguntaRazonSocial);
            Respuesta respuestaRazonSocial = Respuesta.builder().pregunta(preguntaRazonSocial).respuesta(context.formParam("razonSocial")).build();
            respuestas.add(respuestaRazonSocial);

            Pregunta preguntaTipoJuridico = new Pregunta("Tipo juridico");
            formulario.agregarPregunta(preguntaTipoJuridico);
            Respuesta respuestaTipoJuridico = Respuesta.builder().pregunta(preguntaTipoJuridico).respuesta(context.formParam("tipoJuridico")).build();
            respuestas.add(respuestaTipoJuridico);

            Pregunta preguntaRubro = new Pregunta("Rubro");
            formulario.agregarPregunta(preguntaRubro);
            Respuesta respuestaRubro = Respuesta.builder().pregunta(preguntaRubro).respuesta(context.formParam("rubro")).build();
            respuestas.add(respuestaRubro);

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

    public static void asignarTarjetaA(Colaborador colaborador, RepositorioColaboradores repositorioColaboradores) {
        List<Colaborador> colaboradoresHumanos = repositorioColaboradores.getColaboradoresHumanos();
        List<String> codigosAlfanumericos = colaboradoresHumanos.stream().map(colab -> colab.getTarjetaColaborador().getCodigoAlfanumerico()).toList();
        String codigoAlfanumerico = ServiceTarjetas.codigoAlfaNumericoTarjetaColaborador();
        if (codigosAlfanumericos.contains(codigoAlfanumerico)) {
            asignarTarjetaA(colaborador, repositorioColaboradores);
        } else {
            TarjetaColaborador tarjetaColaborador = TarjetaColaborador.builder().codigoAlfanumerico(codigoAlfanumerico).fechaEmision(LocalDateTime.now()).build();
            colaborador.setTarjetaColaborador(tarjetaColaborador);
        }

    }
}
