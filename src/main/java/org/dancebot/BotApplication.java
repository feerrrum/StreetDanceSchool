package org.dancebot;

import org.dancebot.commands.*;
import org.dancebot.telegram.StreetDanceBot;
import org.dancebot.users.UserState;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.dancebot.database.DatabaseHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;

public class BotApplication {

    public static void main(String[] args) throws TelegramApiException, IOException, SQLException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        var data = Files.readAllLines(new File("C:\\Users\\irina\\id.txt").toPath());
        String token = data.get(0);
        var name = data.get(1);
        var bot = new StreetDanceBot(token, name);

        var dbHandler = DatabaseHandler.getInstance();
        bot.addDbHandler(dbHandler);

        var textHandler = new TextCommandHandler();
        textHandler.addCommand(new SimpleTextCommand(UserState.NOT_ON_RECORD,
                dbHandler.getCards(),
                ButtonHelper.coachButtons,
                (s, t) -> s.setState(UserState.CHOOSING)));
        textHandler.addCommand(new ShowScheduleCommand());
        textHandler.addCommand(new EditCommand());
        textHandler.addCommand(new ShowCoachesCommand());
        textHandler.addCommand(new PreDeleteCommand());
        textHandler.addCommand(new DeleteCommand());
        bot.addTextHandler(textHandler);

        var buttonHandler = new TextCommandHandler();
        buttonHandler.addCommand(new ChooseCoachCommand());
        bot.addButtonHandler(buttonHandler);

        telegramBotsApi.registerBot(bot);
    }

}