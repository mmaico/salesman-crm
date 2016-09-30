package br.com.kproj.salesman.administration.users.infrastructure.event;


import br.com.kproj.salesman.administration.users.domain.model.user.User;
import br.com.kproj.salesman.administration.users.domain.model.user.UserEventHandler;
import com.google.common.eventbus.EventBus;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static br.com.kproj.salesman.infrastructure.events.UserChangedMessage.createMessage;

@Component
public class UserEventHandlerEventBus implements UserEventHandler {

    private EventBus eventBus;

    private Gson gson;

    @Autowired
    public UserEventHandlerEventBus(EventBus eventBus, Gson gson) {
        this.eventBus = eventBus;
        this.gson = gson;
    }

    @Override
    public void userChanged(User user) {
        eventBus.post(createMessage(gson.toJson(user)));
    }
}
