package br.com.kproj.salesman.negotiation2.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.accounts.AccountEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessProposalSpringData extends BaseRepositoryLegacy<BusinessProposalEntity, Long> {

    List<BusinessProposalEntity> findAll(@Param("account") AccountEntity account);
}
