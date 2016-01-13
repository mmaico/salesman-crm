package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.notification.ApprovalBusinessProposalNotification;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;

import java.util.Date;

public class ApprovalBusinessProposalNotificationBuilder extends AbstractBuilder<ApprovalBusinessProposalNotification>  {

	public ApprovalBusinessProposalNotificationBuilder() {
		this.entity = new ApprovalBusinessProposalNotification();
	}

	public ApprovalBusinessProposalNotificationBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public ApprovalBusinessProposalNotificationBuilder withNotified(User notified) {
		this.entity.setNotified(notified);
		return this;
	}

    public ApprovalBusinessProposalNotificationBuilder withCreateDate(Date date) {
		this.entity.setCreateDate(date);
		return this;
	}

	public ApprovalBusinessProposalNotificationBuilder setCurrentDate() {
		this.entity.setCreateDate(new Date());
		return this;
	}
    public ApprovalBusinessProposalNotificationBuilder withBusinessProposal(BusinessProposal proposal) {
        this.entity.setProposal(proposal);
        return this;
    }

	public static ApprovalBusinessProposalNotificationBuilder createProposalNotification() {
		return new ApprovalBusinessProposalNotificationBuilder();
	}

}
