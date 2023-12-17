package org.dancebot;

import org.dancebot.commands.*;
import org.dancebot.telegram.StreetDanceBot;
import org.dancebot.users.UserState;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.dancebot.database.DatabaseManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BotApplication {

    public static void main(String[] args) throws TelegramApiException, IOException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        var data = Files.readAllLines(new File("C:\\Users\\tanno\\id.txt").toPath());
        String token = data.get(0);
        var name = data.get(1);
        var bot = new StreetDanceBot(token, name);

        var textHandler = new TextCommandHandler();

        textHandler.addCommand(new SimpleTextCommand(UserState.NEW_BEE, "это ботик :3",
                ButtonHelper.notOnRecordStateButtons,
                (s, t) -> s.setState(UserState.NOT_ON_RECORD)));
        bot.addTextHandler(textHandler);

        var buttonHandler = new TextCommandHandler();
        buttonHandler.addCommand(new ShowMentorsCommand());
        buttonHandler.addCommand(new SimpleButtonReadyStateCommand("Записаться на занятие",
                "??????",
                ButtonHelper.backButtons,
                (s, t) -> s.setState(UserState.CHOOSING)));
        buttonHandler.addCommand(new BackCommand());

        bot.addButtonHandler(buttonHandler);


        telegramBotsApi.registerBot(bot);
    }

}