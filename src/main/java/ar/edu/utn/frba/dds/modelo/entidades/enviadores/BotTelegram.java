package ar.edu.utn.frba.dds.modelo.entidades.enviadores;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
