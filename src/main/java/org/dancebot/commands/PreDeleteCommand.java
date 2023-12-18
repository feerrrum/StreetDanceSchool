package org.dancebot.commands;

import org.dancebot.database.DatabaseHandler;
import org.dancebot.users.UserSession;
import org.dancebot.users.UserState;

import java.io.IOException;
import java.sql.SQLException;

public class PreDeleteCommand implements TextCommand{
    DatabaseHandler dbHandler = new DatabaseHandler();

    public PreDeleteCommand() throws SQLException, IOException {
    }

    @Override
    public boolean canBeApply(UserSession session, String text) {
        return session.getState().equals(UserState.ON_RECORD) && "Удалить".equals(text);
    }

    @Override
    public CommandResult execute(UserSession session, String text) throws SQLException {
        return new CommandResult("Кого?", dbHandler.getNicks());
    }
}
