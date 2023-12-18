package org.dancebot.commands;

import org.dancebot.users.UserSession;
import org.dancebot.users.UserState;

import java.util.List;
import java.util.function.BiConsumer;

public class BackCommand implements TextCommand{
    @Override
    public boolean canBeApply(UserSession session, String text) {
        return "Назад".equals(text);
    }

    @Override
    public CommandResult execute(UserSession session, String text) {
        return new CommandResult("Назад",
                session.getState().equals(UserState.ON_RECORD) ? ButtonHelper.onRecordStateButtons : ButtonHelper.notOnRecordStateButtons);
    }
}
