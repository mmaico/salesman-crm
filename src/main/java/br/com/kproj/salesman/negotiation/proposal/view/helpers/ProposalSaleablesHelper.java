package br.com.kproj.salesman.negotiation.proposal.view.helpers;


import br.com.kproj.salesman.negotiation.proposal.view.dto.session.ProposalSaleablesDTO;
import br.com.kproj.salesman.register.application.contract.saleable.SaleableApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static br.com.kproj.salesman.infrastructure.helpers.NumberHelper.isNegativeNumber;

@Component
public class ProposalSaleablesHelper {

    @Autowired
    private SaleableApplication application;

    @Autowired
    private ProposalSaleablesDTO proposalSaleablesDTO;


    public BigDecimal getTotalItemsOriginalPrice() {

        return proposalSaleablesDTO.getProposalSaleableItemDTOs().stream()
                .map(item -> application.getOne(item.getSaleableId()).get().getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    public BigDecimal getTotalItems() {
        return proposalSaleablesDTO.getProposalSaleableItemDTOs().stream()
                .map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getDiscount() {

        BigDecimal originalItemsPrice = getTotalItemsOriginalPrice();

        BigDecimal discount = originalItemsPrice.subtract(getTotalItems());
        Boolean hasDiscount = isNegativeNumber(discount);

        return hasDiscount ? BigDecimal.ZERO : discount;
    }



}
