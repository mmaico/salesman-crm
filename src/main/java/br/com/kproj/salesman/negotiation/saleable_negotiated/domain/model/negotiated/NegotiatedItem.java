package br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.Saleable;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.SaleablePackage;
import com.trex.shared.annotations.Model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Model
public class NegotiatedItem extends ModelIdentifiable {

    private Long id;

    private Saleable saleable;

    private SaleablePackage saleablePackage;

    @NotNull(message = "domain.saleable.price.is.invalid")
    private BigDecimal price = BigDecimal.ZERO;

    @NotNull(message = "domain.saleable.original.price.is.invalid")
    private BigDecimal originalPrice;

    @NotNull
    @Min(value = 1, message = "quantity.saleable.lessthan.one")
    private Integer quantity = 0;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Saleable getSaleable() {
        return saleable;
    }

    public void setSaleable(Saleable saleable) {
        this.saleable = saleable;
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

    public SaleablePackage getSaleablePackage() {
        return saleablePackage;
    }

    public void setSaleablePackage(SaleablePackage saleablePackage) {
        this.saleablePackage = saleablePackage;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Boolean hasPackage() {
        return saleable == null && saleablePackage != null;
    }

    public Boolean hasSaleableWithPackage() {
        return saleable != null && saleablePackage != null;
    }
}
