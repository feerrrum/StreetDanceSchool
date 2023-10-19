package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Bot extends TelegramLongPollingBot {

    private final String name;

    public Bot(String token, String name) {
        super(token);
        this.name = name;
    }

    public static void main(String[] args) throws TelegramApiException, IOException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        var data = Files.readAllLines(new File("C:\\Users\\irina\\id.txt").toPath());
        var token = data.get(0);
        var name = data.get(1);
        telegramBotsApi.registerBot(new Bot(token, name));
    }

    @Override
    public void onUpdateReceived(Update update) {
        var message = update.getMessage();
        if (!update.hasMessage() || !message.hasText())
            return;

        var chatId = update.getMessage().getChatId();
        SendMessage response = new SendMessage();
        response.setChatId(chatId);
        response.setText("MEOW");
        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return name;
    }
}
