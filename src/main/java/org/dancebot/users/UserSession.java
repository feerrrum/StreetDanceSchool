package org.dancebot.users;

import java.util.List;

public interface UserSession {
    String getId();
    UserState getState();

    void setState(UserState state);
}
