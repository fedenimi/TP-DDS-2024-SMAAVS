package ar.edu.utn.frba.dds.modelo.entidades.enviadores;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class BotTelegram extends TelegramLongPollingBot {
    private String token;
    private String message_text;

    public BotTelegram(String token, String s) {
        this.token = token;
        this.message_text = s;
    }

    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            //String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();


            SendMessage message = SendMessage.builder()
                    .chatId(String.valueOf(chat_id))
                    .text(message_text)
                    .build();
            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotUsername() {
        return "MyAmazingBot";
    }
}
/*
public class BotTelegram extends TelegramLongPollingBot {

    private final String BOT_TOKEN = "6769624373:AAFmSXF1gxjfcF_C2-am4Z0hg6LxRcnWKoM";
    private final String BOT_USERNAME = "tpsmaavsbot";
    private final String CHAT_ID = "+541155656338";

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

    public void sendMessage(String messageText) {
        SendMessage message = new SendMessage();
        message.setChatId(CHAT_ID);
        message.setText(messageText);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
*/

