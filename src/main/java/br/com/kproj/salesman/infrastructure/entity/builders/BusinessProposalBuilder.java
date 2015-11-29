package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;

public class BusinessProposalBuilder extends AbstractBuilder<BusinessProposal>  {

	public BusinessProposalBuilder() {
		this.entity = new BusinessProposal();
	}

	public BusinessProposalBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public static BusinessProposalBuilder createBusinessProposal(Long id) {
		return new BusinessProposalBuilder(id);
	}

	public static BusinessProposalBuilder createBusinessProposal() {
		return new BusinessProposalBuilder();
	}

}
