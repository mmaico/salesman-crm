package br.com.kproj.salesman.register.application.prepare;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;

public interface PreUpdateItems {

    void preUpdate(BusinessProposalEntity proposal);
}
