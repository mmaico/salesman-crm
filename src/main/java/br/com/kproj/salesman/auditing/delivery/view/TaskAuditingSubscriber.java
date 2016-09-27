package br.com.kproj.salesman.auditing.delivery.view;


import br.com.kproj.salesman.infrastructure.events.TaskChangedMessage;
import br.com.kproj.salesman.infrastructure.security.authentication.LoggedUser;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskAuditingSubscriber {


    @Autowired
    private SecurityHelper security;


    @Subscribe
    public void receiveTaskChanged(TaskChangedMessage message) {
        LoggedUser principal = security.getPrincipal();
    }



}
