package br.com.kproj.salesman.negotiation.infrastructure.persistence.translate;

import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalSaleableItem;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.negotiation.domain.model.negotiation.SaleableItem;
import br.com.kproj.salesman.negotiation.domain.model.negotiation.SaleableItemBuilder;
import br.com.kproj.salesman.negotiation.infrastructure.persistence.translate.products.PackageToSaleableConverter;
import br.com.kproj.salesman.negotiation.infrastructure.persistence.translate.products.SaleableUnitEntityToSaleableConverter;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProposalItemEntityToSaleableItemConverter implements Converter<List<ProposalSaleableItem>, List<SaleableItem>> {

    @Autowired
    private PackageToSaleableConverter packageConverter;

    @Autowired
    private SaleableUnitEntityToSaleableConverter saleableConverter;

    @Override
    public List<SaleableItem> convert(List<ProposalSaleableItem> items, Object... args) {

        if (items.isEmpty()) return Lists.newArrayList();

        return items.stream().map(pItem -> convert(pItem)).collect(Collectors.toList());
    }

    private SaleableItem convert(ProposalSaleableItem item) {
        SaleableItemBuilder builder = SaleableItemBuilder.createSaleableItem(item.getId())
                .withOriginalPrice(item.getOriginalPrice())
                .withPrice(item.getPrice())
                .withQuantity(item.getQuantity());

        if (item.hasPackage()) {
            builder.withSaleablePackage(packageConverter.convert(item.getSalePackage()));
        } else {
            builder.withSaleable(saleableConverter.convert(item.getSaleableUnit()));
        }

        return builder.build();

    }
}
