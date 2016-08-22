package br.com.kproj.salesman.infrastructure.events;


public class UserChangedMessage {


    private final String message;

    public UserChangedMessage(String message) {
        this.message = message;
    }

    public static UserChangedMessage createMessage(String message) {
          return new UserChangedMessage(message);
    }

    public String getMessage() {
        return message;
    }


}
