package br.com.kproj.salesman.negotiation.view.dto;


import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackageEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;

import java.math.BigDecimal;

public class SaleableItemDTO {

    private SalePackageEntity ipackage;
    private SaleableUnitEntity saleableUnit;
    private BigDecimal price;
    private Integer quantity;

    public SalePackageEntity getIpackage() {
        return ipackage;
    }

    public void setIpackage(SalePackageEntity ipackage) {
        this.ipackage = ipackage;
    }

    public SaleableUnitEntity getSaleableUnit() {
        return saleableUnit;
    }

    public void setSaleableUnit(SaleableUnitEntity saleableUnit) {
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
