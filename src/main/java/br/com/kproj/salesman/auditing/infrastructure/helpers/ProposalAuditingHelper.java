package br.com.kproj.salesman.auditing.infrastructure.helpers;

import br.com.kproj.salesman.infrastructure.entity.auditing.BusinessProposalAudinting;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.repository.BusinessProposalAuditingRepository;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProposalAuditingHelper {

    @Autowired
    private BusinessProposalAuditingRepository repository;

    public Iterable<BusinessProposalAudinting> findBy(BusinessProposalEntity businessProposalEntity, Pager pager) {

        return repository.findAll(businessProposalEntity.getId(), pager);
    }
}
