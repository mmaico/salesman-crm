package br.com.kproj.salesman.negotiation.proposal.approval.application.subscribers;


import br.com.kproj.salesman.infrastructure.entity.builders.ApproverProfileBuilder;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.ApproverProfile;
import br.com.kproj.salesman.infrastructure.events.messages.UserSaveMessage;
import br.com.kproj.salesman.negotiation.proposal.approval.application.ApproverProfileApplication;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSaveSubscriber {

    @Autowired
    private ApproverProfileApplication application;


    @Subscribe
    public void createOrUpdateApproverProfile(UserSaveMessage message) {
        ApproverProfile profile = ApproverProfileBuilder.createProfile().withApprover(message.getUser()).build();
        application.register(profile);
    }

}
