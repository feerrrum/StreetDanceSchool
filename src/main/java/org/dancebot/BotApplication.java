package org.dancebot;

import org.dancebot.commands.ButtonHelper;
import org.dancebot.commands.SimpleTextCommand;
import org.dancebot.commands.TextCommandHandler;
import org.dancebot.telegram.StreetDanceBot;
import org.dancebot.users.UserState;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class BotApplication {

    public static void main(String[] args) throws TelegramApiException, IOException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        var data = Files.readAllLines(new File("C:\\Users\\irina\\id.txt").toPath());
        String token = data.get(0);
        var name = data.get(1);
        var bot = new StreetDanceBot(token, name);

        var textHandler = new TextCommandHandler();
        textHandler.addCommand(new SimpleTextCommand(UserState.NEW_BEE, "Это ботик :3",
                ButtonHelper.newBeeStateButtons,
                (s, t) -> s.setState(UserState.CHOOSING)));
        bot.addTextHandler(textHandler);


        telegramBotsApi.registerBot(bot);
    }

}