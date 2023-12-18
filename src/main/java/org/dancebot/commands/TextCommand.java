package org.dancebot.commands;

import org.dancebot.users.UserSession;

import java.sql.SQLException;

public interface TextCommand {
    boolean canBeApply(UserSession session, String text);

    CommandResult execute(UserSession session, String text) throws SQLException;
}
