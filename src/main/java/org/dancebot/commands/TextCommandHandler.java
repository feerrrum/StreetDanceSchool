package org.dancebot.commands;

import org.dancebot.users.UserSession;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TextCommandHandler {
    private final List<TextCommand> commands = new ArrayList<>();
    private final CommandResult error = new CommandResult("Я не понимаю :(");
    public void addCommand(TextCommand command) {
        commands.add(command);
    }
    public CommandResult processCommand(UserSession session, String text) {
        var command = commands.stream().filter(c -> c.canBeApply(session, text)).findFirst();
        var result = command.map(c-> {
            try {
                return c.execute(session, text);
            } catch (SQLException e) {
                return error;
            }
        });
        return result.orElse(error);
    }
}
