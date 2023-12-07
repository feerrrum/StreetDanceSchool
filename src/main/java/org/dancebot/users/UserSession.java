package org.dancebot.users;

public interface UserSession {
    UserState getState();

    void setState(UserState state);

}
