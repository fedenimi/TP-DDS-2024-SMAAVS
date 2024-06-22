package ar.edu.utn.frba.dds.modelo.entidades.enviadores;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class GetChatIdBot extends TelegramLongPollingBot {

    private final String BOT_TOKEN = "TU_BOT_TOKEN";
    private final String BOT_USERNAME = "TU_BOT_USERNAME";

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            System.out.println("Chat ID: " + chatId);
        }
    }
}
