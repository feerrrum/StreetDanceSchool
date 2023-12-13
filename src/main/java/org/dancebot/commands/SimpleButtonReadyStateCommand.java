package org.dancebot.commands;

import org.dancebot.users.UserSession;
import org.dancebot.users.UserState;

import java.util.List;
import java.util.function.BiConsumer;

public class SimpleButtonReadyStateCommand extends SimpleTextCommand{
    private final String data;

    public SimpleButtonReadyStateCommand(String data, UserState acceptableState, String resultText, BiConsumer<UserSession, String> action) {
        super(acceptableState, resultText, action);
        this.data = data;
    }

    public SimpleButtonReadyStateCommand(String data, String resultText, List<String> buttons, BiConsumer<UserSession, String> action) {
        super(UserState.CHOOSING, resultText, buttons, action);
        this.data = data;
    }

    public SimpleButtonReadyStateCommand(String data, String resultText, UserState newState) {
        super(UserState.CHOOSING, resultText, newState);
        this.data = data;
    }

    @Override
    public boolean canBeApply(UserSession session, String text) {
        return super.canBeApply(session, text) && text.equals(data);
    }
}
