package br.com.kproj.salesman.auditing.application;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.auditing.BusinessProposalAudinting;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.service.ModelService;

import java.util.Optional;


public interface BusinessProposalAuditingApplication extends ModelService<BusinessProposalAudinting> {

    Optional<BusinessProposalAudinting> registerAuditing(BusinessProposal businessProposal, User userThatChanged);
}
