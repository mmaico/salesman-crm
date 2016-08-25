package br.com.kproj.salesman.negotiation2.domain.model.negotiation;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.negotiation2.domain.model.product.Saleable;
import br.com.kproj.salesman.negotiation2.domain.model.product.SaleablePackage;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


public class SaleableItem extends ModelIdentifiable {

    private Long id;

    private Saleable saleable;

    private SaleablePackage saleablePackage;

    @NotNull(message = "domain.saleable.price.is.invalid")
    private BigDecimal price = BigDecimal.ZERO;

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
}
