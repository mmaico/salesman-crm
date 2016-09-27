package br.com.kproj.salesman.auditing.negotiation.view;


import br.com.kproj.salesman.infrastructure.events.NegotiationPossiblyChanged;
import br.com.kproj.salesman.infrastructure.security.authentication.LoggedUser;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NegotiationAuditingSubscriber {


    @Autowired
    private SecurityHelper security;


    @Subscribe
    public void receiveNegotiationChanged(NegotiationPossiblyChanged message) {
        LoggedUser principal = security.getPrincipal();

    }

}
