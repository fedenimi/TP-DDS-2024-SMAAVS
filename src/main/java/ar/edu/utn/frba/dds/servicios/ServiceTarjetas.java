package ar.edu.utn.frba.dds.servicios;

import java.util.Random;

public class ServiceTarjetas {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int COLABORADORES_LENGTH = 10;
    private static final int VULNERABLES_LENGTH = 11;

    public static String codigoAlfaNumericoAleatorio(int longitud) {
        Random random = new Random();
        StringBuilder code = new StringBuilder(longitud);

        for (int i = 0; i < longitud; i++) {
            int index = random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(index));
        }

        return code.toString();
    }

    public static String codigoAlfaNumericoTarjetaColaborador() {
        return codigoAlfaNumericoAleatorio(COLABORADORES_LENGTH);
    }

    public static String codigoAlfaNumericoTarjetaPersonaVulnerable() {
        return codigoAlfaNumericoAleatorio(VULNERABLES_LENGTH);
    }
}
