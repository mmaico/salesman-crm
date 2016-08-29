package br.com.kproj.salesman.negotiation2.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.accounts.AccountEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.negotiation2.domain.model.account.Account;
import br.com.kproj.salesman.negotiation2.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation2.domain.model.negotiation.NegotiationRepository;
import br.com.kproj.salesman.negotiation2.infrastructure.persistence.springdata.BusinessProposalSpringData;
import br.com.kproj.salesman.negotiation2.infrastructure.persistence.translate.BusinessProposalEntityToNegotiationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.stream.Collectors;

@Repository
public class NegotiationRepositoryHibernate extends BaseRespositoryImpl<Negotiation, BusinessProposalEntity> implements NegotiationRepository {

    @Autowired
    private BusinessProposalEntityToNegotiationConverter converter;

    @Autowired
    private BusinessProposalSpringData repository;

    @Override
    public Collection<Negotiation> findOne(Account account) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(account.getId());

        return repository.findAll(accountEntity).stream()
                .map(proposal -> converter.convert(proposal)).collect(Collectors.toList());
    }

    @Override
    public BaseRepositoryLegacy<BusinessProposalEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<BusinessProposalEntity, Negotiation> getConverter() {
        return converter;
    }
}
