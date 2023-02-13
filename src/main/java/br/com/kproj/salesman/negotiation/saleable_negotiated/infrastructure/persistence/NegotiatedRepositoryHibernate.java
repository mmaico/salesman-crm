package br.com.kproj.salesman.negotiation.saleable_negotiated.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalItemEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.Negotiated;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.NegotiatedRepository;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items.SaleableItem;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items.SaleableItemRepository;
import br.com.kproj.salesman.negotiation.saleable_negotiated.infrastructure.persistence.springdata.ProposalItemSpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.NegotiatedBuilder.createSaleableItem;

import static com.github.mmaico.clone.BusinessModelClone.from;

@Repository("negotiatedRepositoryNegotiatedSaleableModule")
public class NegotiatedRepositoryHibernate extends BaseRespositoryImpl<Negotiated, BusinessProposalItemEntity> implements NegotiatedRepository {

    private ProposalItemSpringData repository;

    private SaleableItemRepository saleableItemRepository;

    @Autowired
    public NegotiatedRepositoryHibernate(ProposalItemSpringData repository, SaleableItemRepository saleableItemRepository) {
        this.repository = repository;
        this.saleableItemRepository = saleableItemRepository;
    }

    @Override
    public Negotiated update(Negotiated negotiated) {
        BusinessProposalItemEntity entityLoaded = repository.findOne(negotiated.getId());
        from(negotiated).merge(entityLoaded);

        return getConverter().convert(entityLoaded);
    }

    @Override
    public Collection<Negotiated> findAll(Negotiation negotiation) {
        Collection<BusinessProposalItemEntity> entities = repository.findAll(negotiation.getId());
        List<Negotiated> negotiateds = entities.stream()
                .map(entity -> getConverter().convert(entity))
                .collect(Collectors.toList());

        return negotiateds;
    }

    @Override
    public Optional<Negotiated> save(Negotiated negotiated) {
        BusinessProposalItemEntity entity = from(negotiated).convertTo(BusinessProposalItemEntity.class);
        entity.setBusinessProposal(new BusinessProposalEntity(negotiated.getNegotiation().getId()));

        return Optional.of(getConverter().convert(repository.save(entity)));
    }

    @Override
    public BaseRepositoryLegacy<BusinessProposalItemEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<BusinessProposalItemEntity, Negotiated> getConverter() {
        return (entity, args) -> {
            Negotiated negotiated = createSaleableItem(entity.getId())
                    .withOriginalPrice(entity.getOriginalPrice())
                    .withPrice(entity.getPrice())
                    .withQuantity(entity.getQuantity())
                    .withNegotiation(entity.getBusinessProposal().getId())
                    .build();

            Collection<SaleableItem> saleableItems = saleableItemRepository.findAll(negotiated);
            negotiated.setSaleableItems(saleableItems);

            return negotiated;
        };
    }


}
