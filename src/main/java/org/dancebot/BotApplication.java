package org.dancebot;

import org.dancebot.commands.ButtonHelper;
import org.dancebot.commands.SimpleTextCommand;
import org.dancebot.commands.TextCommandHandler;
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
        textHandler.addCommand(new SimpleTextCommand(UserState.NEW_BEE, "This is SDS bot :3",
                ButtonHelper.newBeeStateButtons,
                (s, t) -> s.setState(UserState.CHOOSING)));
        bot.addTextHandler(textHandler);

        try (Connection connection = DatabaseManager.getConnection()) {
            // Пример выполнения запроса к базе данных
            String query = "SELECT * FROM sds_table_1";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        // Обработка результатов запроса
                        String dataSQL = resultSet.getString("info");
                        // Дальнейшая обработка данных
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        telegramBotsApi.registerBot(bot);
    }

}