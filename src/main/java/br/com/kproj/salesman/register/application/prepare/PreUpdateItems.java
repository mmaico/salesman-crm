package br.com.kproj.salesman.register.application.prepare;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;

public interface PreUpdateItems {

    void preUpdate(BusinessProposal proposal);
}
