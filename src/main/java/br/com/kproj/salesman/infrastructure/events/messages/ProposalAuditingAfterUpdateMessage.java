package br.com.kproj.salesman.infrastructure.events.messages;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.auditing.BusinessProposalAudinting;


public class ProposalAuditingAfterUpdateMessage {

    private BusinessProposalAudinting before;

    private BusinessProposalAudinting after;

    UserEntity userThatChanged;

    public  ProposalAuditingAfterUpdateMessage(BusinessProposalAudinting before, BusinessProposalAudinting after, UserEntity userThatChanged) {
        this.before = before;
        this.after = after;
        this.userThatChanged = userThatChanged;
    }
    public ProposalAuditingAfterUpdateMessage(){}

    public BusinessProposalAudinting getBefore() {
        return before;
    }

    public void setBefore(BusinessProposalAudinting before) {
        this.before = before;
    }

    public BusinessProposalAudinting getAfter() {
        return after;
    }

    public void setAfter(BusinessProposalAudinting after) {
        this.after = after;
    }

    public UserEntity getUserThatChanged() {
        return userThatChanged;
    }

    public void setUserThatChanged(UserEntity userThatChanged) {
        this.userThatChanged = userThatChanged;
    }

    public static ProposalAuditingAfterUpdateMessage create(BusinessProposalAudinting before, BusinessProposalAudinting after, UserEntity userThatChanged) {
        return new ProposalAuditingAfterUpdateMessage(before, after, userThatChanged);
    }
}
