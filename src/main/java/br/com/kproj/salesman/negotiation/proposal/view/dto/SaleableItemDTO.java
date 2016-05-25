package br.com.kproj.salesman.negotiation.proposal.view.dto;


import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackage;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;

import java.math.BigDecimal;

public class SaleableItemDTO {

    private SalePackage ipackage;
    private SaleableUnit saleableUnit;
    private BigDecimal price;
    private Integer quantity;

    public SalePackage getIpackage() {
        return ipackage;
    }

    public void setIpackage(SalePackage ipackage) {
        this.ipackage = ipackage;
    }

    public SaleableUnit getSaleableUnit() {
        return saleableUnit;
    }

    public void setSaleableUnit(SaleableUnit saleableUnit) {
        this.saleableUnit = saleableUnit;
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
}
