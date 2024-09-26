package ar.edu.utn.frba.dds.modelo.entidades.utils;

import ar.edu.utn.frba.dds.modelo.entidades.enviadores.BotTelegram;
import ar.edu.utn.frba.dds.modelo.entidades.enviadores.EnviadorDeTelegram;
import ar.edu.utn.frba.dds.modelo.entidades.enviadores.EnviadorTelegramAPI;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class MainBotTelegram {
    public static void main(String[] args) throws TelegramApiException {
        EnviadorDeTelegram enviadorDeTelegram = new EnviadorDeTelegram(new EnviadorTelegramAPI("6769624373:AAFmSXF1gxjfcF_C2-am4Z0hg6LxRcnWKoM"));
        enviadorDeTelegram.enviar("1", "HELADERA MEDRANO: ", "2 viandas y se llena");

    }
}
