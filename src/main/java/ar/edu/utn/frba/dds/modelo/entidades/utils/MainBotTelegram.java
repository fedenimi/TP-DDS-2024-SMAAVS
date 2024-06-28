package ar.edu.utn.frba.dds.modelo.entidades.utils;

import ar.edu.utn.frba.dds.modelo.entidades.enviadores.BotTelegram;
import ar.edu.utn.frba.dds.modelo.entidades.enviadores.GetChatIdBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class MainBotTelegram {
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new GetChatIdBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
