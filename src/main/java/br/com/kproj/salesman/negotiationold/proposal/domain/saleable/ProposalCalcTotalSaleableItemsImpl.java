package br.com.kproj.salesman.negotiationold.proposal.domain.saleable;


import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalSaleableItem;
import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackageEntity;
import br.com.kproj.salesman.infrastructure.repository.Saleable.SalesPackageRepository;
import br.com.kproj.salesman.negotiationold.proposal.domain.saleable.contract.ProposalCalcTotalSaleableItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service(value = "proposalCalcTotalSaleableItems")
public class ProposalCalcTotalSaleableItemsImpl implements ProposalCalcTotalSaleableItems {

    @Autowired
    private SalesPackageRepository salesPackageRepository;

    @Override
    public BigDecimal getTotal(List<ProposalSaleableItem> items) {

        return items.stream()
                .map(e -> calcValue(e))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    private BigDecimal calcValue(ProposalSaleableItem item) {

        if (item.hasSaleableWithPackage() || item.hasPackage()) {
            Optional<SalePackageEntity> salePackage = salesPackageRepository.getOne(item.getSalePackage().getId());
            if (salePackage.get().calcPriceByProducts()) {
                if (item.hasPackage()) {
                    return BigDecimal.ZERO;
                } else {
                    return item.getPrice().multiply(new BigDecimal(item.getQuantity()));
                }
            } else {
                if (item.hasPackage()) {
                    return item.getPrice().multiply(new BigDecimal(item.getQuantity()));
                } else {
                    return BigDecimal.ZERO;
                }
            }
        } else {
            return item.getPrice().multiply(new BigDecimal(item.getQuantity()));
        }

    }
}
