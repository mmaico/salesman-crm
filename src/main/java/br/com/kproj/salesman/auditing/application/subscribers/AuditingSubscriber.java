package br.com.kproj.salesman.auditing.application.subscribers;


import br.com.kproj.salesman.auditing.application.BusinessProposalAuditingApplication;
import br.com.kproj.salesman.infrastructure.entity.auditing.BusinessProposalAudinting;
import br.com.kproj.salesman.infrastructure.entity.builders.BusinessProposalAuditingBuilder;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.repository.BusinessProposalAuditingRepository;
import br.com.kproj.salesman.infrastructure.security.authentication.LoggedUser;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import com.google.common.eventbus.Subscribe;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuditingSubscriber {

    @Autowired
    private BusinessProposalAuditingApplication application;

    @Autowired
    private SecurityHelper security;


    @Subscribe
    public void persistAuditWhenDistinctLastVersion(BusinessProposal proposal) {
        LoggedUser principal = security.getPrincipal();
        application.registerAuditing(proposal, principal.getUser());
    }

}
