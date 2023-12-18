package org.dancebot.commands;

import org.dancebot.users.UserSession;
import org.dancebot.users.UserState;

import java.util.List;
import java.util.function.BiConsumer;

public class SimpleTextCommand implements TextCommand {

    private final UserState acceptableState;
    private final CommandResult result;
    private final BiConsumer<UserSession, String> action;

    public SimpleTextCommand(UserState acceptableState, List<String> results, List<String> buttons, BiConsumer<UserSession, String> action) {
        this.acceptableState = acceptableState;
        this.result = new CommandResult(results, buttons);
        this.action = action;
    }

    @Override
    public boolean canBeApply(UserSession session, String text) {
        return session.getState() == acceptableState;
    }

    @Override
    public CommandResult execute(UserSession session, String text) {
        action.accept(session, text);
        return result;
    }
}