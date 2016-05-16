package br.com.kproj.salesman.auditing.application;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.auditing.BusinessProposalAudinting;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.infrastructure.service.ModelService;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;


public interface BusinessProposalAuditingApplication extends ModelService<BusinessProposalAudinting> {

    Optional<BusinessProposalAudinting> registerAuditing(BusinessProposal businessProposal, User userThatChanged);

    Page<BusinessProposalAudinting> findLogs(Long businessEntityId, Pager pager);


}
