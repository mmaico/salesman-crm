package br.com.kproj.salesman.administration.approval_negotiation.infrastructure.persistence;


import br.com.kproj.salesman.administration.approval_negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.negotiation.NegotiationRepository;
import br.com.kproj.salesman.administration.approval_negotiation.infrastructure.persistence.springdata.BusinessProposalEntityRepository;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NegotiationRepositoryHibernate extends BaseRespositoryImpl<Negotiation, BusinessProposalEntity> implements NegotiationRepository {

    @Autowired
    private BusinessProposalEntityRepository repository;


    @Override
    public BaseRepositoryLegacy<BusinessProposalEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<BusinessProposalEntity, Negotiation> getConverter() {
        return ((proposalEntity, args) -> new Negotiation(proposalEntity.getId()));
    }


}
