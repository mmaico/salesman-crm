package br.com.kproj.salesman.negotiation.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.accounts.AccountEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessProposalSpringData extends BaseRepositoryLegacy<BusinessProposalEntity, Long> {

    @Query("SELECT b FROM BusinessProposalEntity AS b WHERE b.account = :account")
    List<BusinessProposalEntity> findAll(@Param("account") AccountEntity account);
}
