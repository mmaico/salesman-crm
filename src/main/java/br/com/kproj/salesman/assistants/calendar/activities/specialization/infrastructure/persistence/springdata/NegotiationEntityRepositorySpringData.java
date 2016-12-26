package br.com.kproj.salesman.assistants.calendar.activities.specialization.infrastructure.persistence.springdata;


import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("negotiationRepositoryCalendarModule")
public interface NegotiationEntityRepositorySpringData extends BaseRepositoryLegacy<BusinessProposalEntity, Long> {



}
