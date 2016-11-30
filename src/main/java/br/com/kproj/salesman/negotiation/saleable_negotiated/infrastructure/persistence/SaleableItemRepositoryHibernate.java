package br.com.kproj.salesman.negotiation.saleable_negotiated.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalItemEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalSaleableItemEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackageEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.Negotiated;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.Saleable;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.SaleablePackage;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.SaleableRepository;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items.SaleableItem;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items.SaleableItemRepository;
import br.com.kproj.salesman.negotiation.saleable_negotiated.infrastructure.persistence.springdata.SaleableItemRepositorySpringData;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SaleableItemRepositoryHibernate extends BaseRespositoryImpl<SaleableItem, ProposalSaleableItemEntity> implements SaleableItemRepository {

    private SaleableItemRepositorySpringData repository;
    private SaleableRepository saleableRepository;

    @Autowired
    public SaleableItemRepositoryHibernate(SaleableItemRepositorySpringData repository, SaleableRepository saleableRepository) {
        this.repository = repository;
        this.saleableRepository = saleableRepository;
    }

    @Override
    public List<SaleableItem> generateBy(Saleable saleable, Negotiated negotiated) {
        Optional<Saleable> saleableLoaded = saleableRepository.findOne(saleable.getId());

        if (saleableLoaded.get() instanceof SaleablePackage) {
            SaleablePackage saleablePackage = (SaleablePackage) saleableLoaded.get();

            List<SaleableItem> items = saleablePackage.getSaleables().stream()
                    .map(saleableInPackge -> {
                        ProposalSaleableItemEntity itemEntity = convert(saleableInPackge, negotiated, saleablePackage);
                        return repository.save(itemEntity);
                    }).collect(Collectors.toList()).stream()
                        .map(itemSaved -> getConverter().convert(itemSaved))
                    .collect(Collectors.toList());
            return items;
        } else {
            ProposalSaleableItemEntity itemEntity = convert(saleable, negotiated, null);
            return Lists.newArrayList(getConverter().convert(repository.save(itemEntity)));
        }
    }

    private ProposalSaleableItemEntity convert(Saleable saleable, Negotiated negotiated, SaleablePackage sPackage) {
        ProposalSaleableItemEntity itemEntity = new ProposalSaleableItemEntity();
        itemEntity.setNegotiated(new BusinessProposalItemEntity(negotiated.getId()));
        itemEntity.setSaleable(new SaleableUnitEntity(saleable.getId()));
        itemEntity.setUsedPackage(sPackage != null ? new SalePackageEntity(sPackage.getId()) : null);

        return itemEntity;
    }

    @Override
    public Converter<ProposalSaleableItemEntity, SaleableItem> getConverter() {
        return ((saleableItemEntity, args) -> {
            SaleableItem saleableItem = new SaleableItem();
            saleableItem.setId(saleableItemEntity.getId());
            saleableItem.setNegotiated(new Negotiated(saleableItemEntity.getNegotiated().getId()));
            saleableItem.setSaleable(new Saleable(saleableItemEntity.getSaleable().getId()));

            if (saleableItemEntity.getUsedPackage() != null) {
                saleableItem.setUsedPackage(new SaleablePackage(saleableItemEntity.getUsedPackage().getId()));
            }
            return saleableItem;
        });
    }

    @Override
    public BaseRepositoryLegacy<ProposalSaleableItemEntity, Long> getRepository() {
        return repository;
    }
}
