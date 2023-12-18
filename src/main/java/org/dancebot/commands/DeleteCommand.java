package org.dancebot.commands;

import org.dancebot.database.DatabaseHandler;
import org.dancebot.users.UserSession;
import org.dancebot.users.UserState;

import java.io.IOException;
import java.sql.SQLException;

public class DeleteCommand implements TextCommand{
    DatabaseHandler dbHandler = DatabaseHandler.getInstance();

    public DeleteCommand() throws SQLException, IOException {
    }

    @Override
    public boolean canBeApply(UserSession session, String text) {
        return session.getState().equals(UserState.ON_RECORD);
    }

    @Override
    public CommandResult execute(UserSession session, String text) throws SQLException {
        String coachId = String.valueOf(text.charAt(0));
        if (dbHandler.hasThatCoach(session.getId(), coachId)) {
            dbHandler.deleteCoach(session.getId(), coachId);
            return new CommandResult("Успешно", ButtonHelper.onRecordStateButtons);
        }
        return new CommandResult("Не является вашим тренером", ButtonHelper.onRecordStateButtons);
    }
}
