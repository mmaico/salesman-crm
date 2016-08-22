package br.com.kproj.salesman.administration.users.infrastructure.service;


import br.com.kproj.salesman.administration.users.domain.model.user.User;
import br.com.kproj.salesman.administration.users.domain.service.UserEventService;
import com.google.common.eventbus.EventBus;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static br.com.kproj.salesman.infrastructure.events.UserChangedMessage.createMessage;

@Component
public class UserEventBusService implements UserEventService {

    @Autowired
    private EventBus eventBus;

    @Autowired
    private Gson gson;

    @Override
    public void userChanged(User user) {
        eventBus.post(createMessage(gson.toJson(user)));
    }
}
