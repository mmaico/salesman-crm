package br.com.kproj.salesman.negotiation.saleable_negotiated.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiation.NegotiationRepository;
import br.com.kproj.salesman.negotiation.saleable_negotiated.infrastructure.persistence.springdata.BusinessProposalSpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("negotiationRepositoryNegotiatedSaleableModule")
public class NegotiationRepositoryHibernate extends BaseRespositoryImpl<Negotiation, BusinessProposalEntity> implements NegotiationRepository {



    private BusinessProposalSpringData repository;

    @Autowired
    public NegotiationRepositoryHibernate(BusinessProposalSpringData repository) {
        this.repository = repository;
    }


    @Override
    public BaseRepositoryLegacy<BusinessProposalEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<BusinessProposalEntity, Negotiation> getConverter() {
        return null;
    }
}
