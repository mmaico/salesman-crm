package br.com.kproj.salesman.auditing.application;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.auditing.BusinessProposalAudinting;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.repository.Pager;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;
import org.springframework.data.domain.Page;

import java.util.Optional;


public interface BusinessProposalAuditingApplication extends ModelLegacyService<BusinessProposalAudinting> {

    Optional<BusinessProposalAudinting> registerAuditing(BusinessProposalEntity businessProposalEntity, UserEntity userThatChanged);

    Page<BusinessProposalAudinting> findLogs(Long businessEntityId, Pager pager);


}
