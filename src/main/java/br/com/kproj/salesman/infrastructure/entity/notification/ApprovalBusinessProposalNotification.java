package br.com.kproj.salesman.infrastructure.entity.notification;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("approval_business_proposal_notification")
public class ApprovalBusinessProposalNotification extends Notification {

    /**
     *
     */
    private static final long serialVersionUID = 2728388686834419769L;

    @ManyToOne
    @JoinColumn(name="proposal_id")
    private BusinessProposalEntity proposal;


    public BusinessProposalEntity getProposal() {
        return proposal;
    }

    public void setProposal(BusinessProposalEntity proposal) {
        this.proposal = proposal;
    }
}
