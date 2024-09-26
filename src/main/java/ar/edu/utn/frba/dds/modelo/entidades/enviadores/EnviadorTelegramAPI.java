package ar.edu.utn.frba.dds.modelo.entidades.enviadores;

import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
@AllArgsConstructor
public class EnviadorTelegramAPI implements AdapterEnviadorTelegram{
    private String botToken;
    @Override
    public void enviar(String numero, String titulo, String mensaje) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new BotTelegram(botToken, titulo + "\n" + mensaje));
            System.out.println("MyAmazingBot successfully started!");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
