package br.com.kproj.salesman.auditing.application.subscribers;


import br.com.kproj.salesman.auditing.application.BusinessProposalAuditingApplication;
import br.com.kproj.salesman.auditing.application.TaskAuditingApplication;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.events.messages.TaskChangeMessage;
import br.com.kproj.salesman.infrastructure.security.authentication.LoggedUser;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuditingSubscriber {

    @Autowired
    private BusinessProposalAuditingApplication application;

    @Autowired
    private TaskAuditingApplication taskAuditingApplication;

    @Autowired
    private SecurityHelper security;


    @Subscribe
    public void persistAuditWhenDistinctLastVersion(BusinessProposalEntity proposal) {
        LoggedUser principal = security.getPrincipal();
        //application.registerAuditing(proposal, principal.getUser());
    }

    @Subscribe
    public void persistAuditWhenDistinctLastVersion(TaskChangeMessage taskChangeMessage) {
        LoggedUser principal = security.getPrincipal();

        //taskAuditingApplication.registerAuditing(taskChangeMessage.getTask(), principal.getUser());
    }



}
