package br.com.kproj.salesman.negotiation.negotiation.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.accounts.CustomerEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.ProposalTemperature;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.customer.Customer;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation.NegotiationRepository;
import br.com.kproj.salesman.negotiation.negotiation.infrastructure.persistence.springdata.BusinessProposalSpringData;
import br.com.kproj.salesman.negotiation.negotiation.infrastructure.persistence.translate.BusinessProposalEntityToNegotiationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.trex.clone.BusinessModelClone.from;

@Repository("negotiationRepositoryNegotiationModule")
public class NegotiationRepositoryHibernate extends BaseRespositoryImpl<Negotiation, BusinessProposalEntity> implements NegotiationRepository {


    private BusinessProposalEntityToNegotiationConverter converter;

    private BusinessProposalSpringData repository;

    @Autowired
    public NegotiationRepositoryHibernate(BusinessProposalEntityToNegotiationConverter converter, BusinessProposalSpringData repository) {
        this.converter = converter;
        this.repository = repository;
    }


    @Override
    public Optional<Negotiation> save(Negotiation negotiation) {
        BusinessProposalEntity resultEntity = from(negotiation).convertTo(BusinessProposalEntity.class);
        resultEntity.setTemperature(ProposalTemperature.valueOf(negotiation.getTemperature().name()));

        return Optional.ofNullable(getConverter().convert(getRepository().save(resultEntity)));
    }

    @Override
    public Collection<Negotiation> findOne(Customer account) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(account.getId());

        return repository.findAll(customerEntity).stream()
                .map(proposal -> converter.convert(proposal)).collect(Collectors.toList());
    }

    @Override
    public Negotiation update(Negotiation negotiation) {
        BusinessProposalEntity entity = repository.findOne(negotiation.getId());
        from(negotiation).merge(entity);

        return getConverter().convert(entity);
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
