package org.dancebot.commands;

import org.dancebot.database.DatabaseHandler;
import org.dancebot.users.UserSession;
import org.dancebot.users.UserState;

import java.io.IOException;
import java.sql.SQLException;

public class ShowScheduleCommand implements TextCommand{
    DatabaseHandler dbHandler = DatabaseHandler.getInstance();

    public ShowScheduleCommand() throws SQLException, IOException {
    }

    @Override
    public boolean canBeApply(UserSession session, String text) {
        return "Расписание".equals(text) && session.getState().equals(UserState.ON_RECORD);
    }

    @Override
    public CommandResult execute(UserSession session, String text) throws SQLException {
        if (dbHandler.isOnRecord(session.getId())) {
            return new CommandResult(dbHandler.getSchedule(session.getId()));
        }
        return new CommandResult("Вы ни к кому не записаны", ButtonHelper.onRecordStateButtons);
    }
}
