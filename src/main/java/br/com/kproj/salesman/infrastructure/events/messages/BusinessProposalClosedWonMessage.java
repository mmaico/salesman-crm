package br.com.kproj.salesman.infrastructure.events.messages;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;

public class BusinessProposalClosedWonMessage {

    private BusinessProposal proposal;

    public BusinessProposalClosedWonMessage(BusinessProposal proposal) {
        this.proposal = proposal;
    }

    public static BusinessProposalClosedWonMessage create(BusinessProposal proposal) {
        return new BusinessProposalClosedWonMessage(proposal);
    }

    public BusinessProposal getProposal() {
        return proposal;
    }

}

