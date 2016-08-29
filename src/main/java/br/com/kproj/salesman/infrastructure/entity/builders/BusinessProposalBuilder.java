package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;

public class BusinessProposalBuilder extends AbstractBuilder<BusinessProposalEntity>  {

	public BusinessProposalBuilder() {
		this.entity = new BusinessProposalEntity();
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
