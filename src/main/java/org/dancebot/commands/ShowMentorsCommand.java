package org.dancebot.commands;

import org.dancebot.users.UserSession;

public class ShowMentorsCommand implements TextCommand{
    @Override
    public boolean canBeApply(UserSession session, String text) {
        return "Записаться на занятие".equals(text);
    }

    @Override
    public CommandResult execute(UserSession session, String text) {
        return new CommandResult("Список", ButtonHelper.backButtons);
    }
}
