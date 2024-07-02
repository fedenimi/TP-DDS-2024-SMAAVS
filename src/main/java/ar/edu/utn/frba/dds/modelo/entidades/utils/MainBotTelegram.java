package ar.edu.utn.frba.dds.modelo.entidades.utils;

import ar.edu.utn.frba.dds.modelo.entidades.enviadores.BotTelegram;
import ar.edu.utn.frba.dds.modelo.entidades.enviadores.EnviadorDeTelegram;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class MainBotTelegram {
    public static void main(String[] args) {
        EnviadorDeTelegram enviadorDeTelegram = new EnviadorDeTelegram("6769624373:AAFmSXF1gxjfcF_C2-am4Z0hg6LxRcnWKoM");
        enviadorDeTelegram.enviar("1", "HELADERA MEDRANO: ", "Quedan 5 viandas");
        /*
        String botToken = "6769624373:AAFmSXF1gxjfcF_C2-am4Z0hg6LxRcnWKoM";
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new BotTelegram(botToken, titulo + mensaje));
            System.out.println("MyAmazingBot successfully started!");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        */

    }
}
