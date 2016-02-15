package br.com.kproj.salesman.negotiation.view.dto;

import br.com.kproj.salesman.infrastructure.configuration.ServiceLocator;
import br.com.kproj.salesman.infrastructure.entity.builders.ProposalSaleableItemBuilder;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalSaleableItem;
import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackage;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.negotiation.view.dto.SaleableItemDTO;
import br.com.kproj.salesman.negotiation.view.dto.session.ProposalSaleableItemDTO;
import br.com.kproj.salesman.negotiation.view.dto.session.ProposalSaleablesDTO;
import br.com.kproj.salesman.register.application.contract.saleable.SalePackageApplication;
import br.com.kproj.salesman.register.application.contract.saleable.SaleableApplication;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public class BusinessProposalRequestMergeHelper {







    public static BusinessProposal merge(ProposalSaleablesDTO dto, BusinessProposal proposal) {

        List<ProposalSaleableItem> saleableItems = Lists.newArrayList();
        SaleableApplication saleableApplication = ServiceLocator.getBean(SaleableApplication.class);
        SalePackageApplication packageApplication = ServiceLocator.getBean(SalePackageApplication.class);

        for (ProposalSaleableItemDTO item : dto.getProposalSaleableItemDTOs()) {

            if (item.getPackageItems().isEmpty()) {
                ProposalSaleableItem proposalSaleableItem = ProposalSaleableItemBuilder.createProposalSaleable(item.getId())
                        .withPrice(item.getPrice())
                        .withQuantity(item.getQuantity())
                        .withOriginalPrice(saleableApplication.getOne(item.getSaleableId()).get().getPrice())
                        .withSaleable(new SaleableUnit(item.getSaleableId())).build();

                saleableItems.add(proposalSaleableItem);
            } else {
                Optional<SalePackage> salePackage = packageApplication.getOne(item.getSaleableId());

                //add package
                saleableItems.add(ProposalSaleableItemBuilder.createProposalSaleable(item.getId())
                        .withPackage(new SalePackage(item.getSaleableId()))
                        .withPrice(item.getPrice())
                        .withQuantity(item.getQuantity())
                        .withOriginalPrice(salePackage.get().calcPriceByProducts() ? BigDecimal.ZERO : salePackage.get().getPrice())
                        .build()
                );
                 //add items package

                item.getPackageItems().stream().filter(subItem -> subItem.isSelected()).forEach(subitems ->

                       saleableItems.add(ProposalSaleableItemBuilder.createProposalSaleable(subitems.getId())
                            .withPackage(new SalePackage(item.getSaleableId()))
                            .withPrice(subitems.getPrice())
                            .withOriginalPrice(saleableApplication.getOne(item.getSaleableId()).get().getPrice())
                            .withQuantity(subitems.getQuantity())
                            .withSaleable(new SaleableUnit(subitems.getSaleableId())).build()
                       )
                );
            }

        }

        proposal.setSaleableItems(saleableItems);

        return proposal;
    }


}
