package br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.Saleable;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items.GenerateSaleableItems;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items.SaleableItem;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items.SaleableItemRepository;
import com.google.common.collect.Lists;
import com.trex.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Model
public class Negotiated extends ModelIdentifiable {

    private Long id;

    @NotNull(message = "domain.saleable.price.is.invalid")
    private BigDecimal price = BigDecimal.ZERO;

    @NotNull(message = "domain.saleable.original.price.is.invalid")
    private BigDecimal originalPrice;

    @NotNull
    @Min(value = 1, message = "quantity.saleable.lessthan.one")
    private Integer quantity = 0;

    private Negotiation negotiation;

    private Collection<SaleableItem> saleableItems = Lists.newArrayList();

    @Autowired
    private SaleableItemRepository saleableItemRepository;

    public Negotiated(){}
    public Negotiated(Long id) {
        this.id = id;
    }

    public List<SaleableItem> generate(GenerateSaleableItems saleableItems) {
        Saleable saleable = saleableItems.getSaleable();
        return saleableItemRepository.generateBy(saleable, this);
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Negotiation getNegotiation() {
        return negotiation;
    }

    public void setNegotiation(Negotiation negotiation) {
        this.negotiation = negotiation;
    }

    public Collection<SaleableItem> getSaleableItems() {
        return saleableItems;
    }

    public void setSaleableItems(Collection<SaleableItem> saleableItems) {
        this.saleableItems = saleableItems;
    }
}
