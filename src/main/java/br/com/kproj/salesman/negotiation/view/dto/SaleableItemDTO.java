package br.com.kproj.salesman.negotiation.view.dto;


import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.infrastructure.entity.saleable.Package;

import java.math.BigDecimal;

public class SaleableItemDTO {

    private Package ipackage;
    private SaleableUnit saleableUnit;
    private BigDecimal price;
    private Integer quantity;

    public Package getIpackage() {
        return ipackage;
    }

    public void setIpackage(Package ipackage) {
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
