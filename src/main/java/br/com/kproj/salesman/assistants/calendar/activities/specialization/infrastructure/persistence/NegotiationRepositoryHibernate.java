package br.com.kproj.salesman.assistants.calendar.activities.specialization.infrastructure.persistence;


import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.negotiation.NegotiationRepository;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.infrastructure.persistence.springdata.NegotiationEntityRepositorySpringData;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("negotiationRepositoryHibernateCalendarModule")
public class NegotiationRepositoryHibernate extends BaseRespositoryImpl<Negotiation, BusinessProposalEntity> implements NegotiationRepository {


    private NegotiationEntityRepositorySpringData repository;

    @Autowired
    public NegotiationRepositoryHibernate(NegotiationEntityRepositorySpringData repository) {
        this.repository = repository;
    }

    @Override
    public BaseRepositoryLegacy<BusinessProposalEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<BusinessProposalEntity, Negotiation> getConverter() {
        return ((negotiationEntity, args) -> new Negotiation(negotiationEntity.getId()));
    }
}
