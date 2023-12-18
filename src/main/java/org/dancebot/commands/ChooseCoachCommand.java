package org.dancebot.commands;

import org.dancebot.database.DatabaseHandler;
import org.dancebot.users.UserSession;
import org.dancebot.users.UserState;

import java.io.IOException;
import java.sql.SQLException;

public class ChooseCoachCommand implements TextCommand{
    DatabaseHandler dbHandler = new DatabaseHandler();

    public ChooseCoachCommand() throws SQLException, IOException {
    }

    @Override
    public boolean canBeApply(UserSession session, String text) {
        return session.getState().equals(UserState.CHOOSING);
    }

    @Override
    public CommandResult execute(UserSession session, String text) throws SQLException {
        session.setState(UserState.ON_RECORD);
        if (dbHandler.hasThatCoach(session.getId(), text)) {
            return new CommandResult("no", ButtonHelper.editButtons);
        }
        dbHandler.addCoach(session.getId(), text);
        return new CommandResult("Поздравляю!\nВы записаны",
                ButtonHelper.onRecordStateButtons);
    }
}
