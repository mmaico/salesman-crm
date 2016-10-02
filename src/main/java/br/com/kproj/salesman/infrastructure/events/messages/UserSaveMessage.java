package br.com.kproj.salesman.infrastructure.events.messages;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;

@Deprecated
public class UserSaveMessage {


    private UserEntity user;

    public UserSaveMessage(UserEntity user) {
        this.user = user;
    }

    public static UserSaveMessage create(UserEntity userChange) {
          return new UserSaveMessage(userChange);
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}
