package br.com.kproj.salesman.sales.view.dto;

import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalSaleableItem;
import com.google.common.collect.Lists;

import java.util.List;


public class SaleDTO {

    private BusinessProposalEntity proposal = new BusinessProposalEntity();

    private List<SaleableItemDTO> items = Lists.newArrayList();

    public List<SaleableItemDTO> getItems() {
        return items;
    }

    public void setItems(List<SaleableItemDTO> items) {
        this.items = items;
    }

    public BusinessProposalEntity get() {

        List<ProposalSaleableItem> saleableItems = Lists.newArrayList();

        for (SaleableItemDTO dto: items) {
//            ProposalSaleableItem item = SaleableItemBuilder.create()
//                   // .withPackage(dto.getIpackage())
//                    .withPrice(dto.getPrice())
//                    .withQuantity(dto.getQuantity()).build();
//                    //.withSaleable(dto.getSaleableUnit()).build();

          //  saleableItems.add(item);
        }

        proposal.setSaleableItems(saleableItems);

        return proposal;
    }

    public BusinessProposalEntity getProposal() {
        return proposal;
    }

    public void setProposal(BusinessProposalEntity proposal) {
        this.proposal = proposal;
    }
}
