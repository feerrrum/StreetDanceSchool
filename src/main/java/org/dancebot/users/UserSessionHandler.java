package org.dancebot.users;


public class UserSessionHandler implements UserSession{
    private final UserId id;
    private UserState state = UserState.NOT_ON_RECORD;
    UserSessionHandler(UserId id) {
        this.id = id;
    }
    @Override
    public String getId() {
        return String.valueOf(id.id());
    }
    @Override
    public UserState getState() {
        return state;
    }

    @Override
    public void setState(UserState state) {
        this.state = state;
    }
}
