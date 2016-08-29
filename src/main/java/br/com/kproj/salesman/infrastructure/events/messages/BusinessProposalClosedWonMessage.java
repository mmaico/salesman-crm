package br.com.kproj.salesman.infrastructure.events.messages;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;

public class BusinessProposalClosedWonMessage {

    private BusinessProposalEntity proposal;

    public BusinessProposalClosedWonMessage(BusinessProposalEntity proposal) {
        this.proposal = proposal;
    }

    public static BusinessProposalClosedWonMessage create(BusinessProposalEntity proposal) {
        return new BusinessProposalClosedWonMessage(proposal);
    }

    public BusinessProposalEntity getProposal() {
        return proposal;
    }

}

