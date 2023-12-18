package org.dancebot.users;


public interface UserSession {
    String getId();
    UserState getState();

    void setState(UserState state);
}
