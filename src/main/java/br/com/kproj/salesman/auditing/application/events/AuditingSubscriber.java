package br.com.kproj.salesman.auditing.application.events;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import com.google.common.eventbus.Subscribe;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuditingSubscriber {

    @Autowired
    private Gson gson;

    @Subscribe
    public void persistAuditWhenDistinctBeforeVersion(BusinessProposal proposal) {
        String json = gson.toJson(proposal);
    }

}
