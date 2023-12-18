package org.dancebot.commands;

import org.dancebot.users.UserSession;
import org.dancebot.users.UserState;

public class EditCommand implements TextCommand{
    @Override
    public boolean canBeApply(UserSession session, String text) {
        return session.getState().equals(UserState.ON_RECORD) && "Изменить".equals(text);
    }

    @Override
    public CommandResult execute(UserSession session, String text) {
        return new CommandResult("Что хотите сделать?", ButtonHelper.editButtons);
    }
}
