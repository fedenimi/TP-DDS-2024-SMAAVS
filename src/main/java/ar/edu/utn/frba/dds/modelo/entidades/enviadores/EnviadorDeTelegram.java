package ar.edu.utn.frba.dds.modelo.entidades.enviadores;

import lombok.AllArgsConstructor;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;

import static jdk.javadoc.internal.tool.Main.execute;

@AllArgsConstructor
public class EnviadorDeTelegram extends TelegramLongPollingBot implements Enviador{
  private String botToken;
    @Override
    public void enviar(String numero, String titulo, String mensaje) throws TelegramApiException {
        long chat_id = 1618006899;


        SendMessage message = SendMessage.builder()
                .chatId(String.valueOf(chat_id))
                .text("hola")
                .build();
        execute(message); // Sending our message object to user
        /*
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new BotTelegram(botToken, titulo + "\n" + mensaje));
            System.out.println("MyAmazingBot successfully started!");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        */

    }

    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public String getBotToken() {
        return null;
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }
}
