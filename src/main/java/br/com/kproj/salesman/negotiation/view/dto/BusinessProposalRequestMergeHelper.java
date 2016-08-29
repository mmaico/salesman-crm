package br.com.kproj.salesman.negotiation.view.dto;

import br.com.kproj.salesman.infrastructure.configuration.ServiceLocator;
import br.com.kproj.salesman.infrastructure.entity.builders.SaleableItemBuilder;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalSaleableItem;
import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackageEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation.domain.model.negotiation.SaleableItem;
import br.com.kproj.salesman.negotiation.domain.model.product.*;
import br.com.kproj.salesman.negotiation.view.dto.session.ProposalSaleableItemDTO;
import br.com.kproj.salesman.negotiation.view.dto.session.ProposalSaleablesDTO;
import br.com.kproj.salesman.register.application.contract.saleable.SalePackageApplication;
import br.com.kproj.salesman.register.application.contract.saleable.SaleableApplication;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public class BusinessProposalRequestMergeHelper {




    public static Negotiation merge(ProposalSaleablesDTO dto, Negotiation negotiation) {

        List<SaleableItem> saleableItems = Lists.newArrayList();

        SaleableRepository saleableRepository = ServiceLocator.getBean(SaleableRepository.class);


        for (ProposalSaleableItemDTO item : dto.getProposalSaleableItemDTOs()) {

            if (item.getPackageItems().isEmpty()) {
                SaleableItem saleableItem = SaleableItemBuilder.createProposalSaleable(item.getId())
                        .withPrice(item.getPrice())
                        .withQuantity(item.getQuantity())
                        .withOriginalPrice(saleableRepository.findOne(item.getSaleableId()).get().getPrice())
                        .withSaleable(SaleableBuilder.createSaleable(item.getSaleableId()).build()).build();

                saleableItems.add(saleableItem);
            } else {
                Optional<Saleable> salePackage = saleableRepository.findOne(item.getSaleableId());

                //add package
                saleableItems.add(SaleableItemBuilder.createProposalSaleable(item.getId())
                        .withPackage(SaleablePackageBuilder.createPackage(item.getSaleableId()).build())
                        .withPrice(item.getPrice())
                        .withQuantity(item.getQuantity())
                        .withOriginalPrice(salePackage.get().getPrice())
                        .build()
                );
                 //add items package

                item.getPackageItems().stream().filter(subItem -> subItem.isSelected()).forEach(subitems ->

                       saleableItems.add(SaleableItemBuilder.createProposalSaleable(subitems.getId())
                            .withPackage(SaleablePackageBuilder.createPackage(item.getSaleableId()).build())
                            .withPrice(subitems.getPrice())
                            .withOriginalPrice(saleableRepository.findOne(item.getSaleableId()).get().getPrice())
                            .withQuantity(subitems.getQuantity())
                            .withSaleable(SaleableBuilder.createSaleable(subitems.getSaleableId()).build()).build()
                       )
                );
            }

        }

        negotiation.setSaleablesItems(saleableItems);

        return negotiation;
    }


}
