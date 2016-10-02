package br.com.kproj.salesman.sales.application.dto;

import java.math.BigDecimal;


public class SaleableItemDTO {

    private Long id;

    private SaleableDTO saleable;

    private SaleablePackageDTO saleablePackage;

    private BigDecimal price = BigDecimal.ZERO;

    private BigDecimal originalPrice;

    private Integer quantity = 0;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SaleableDTO getSaleable() {
        return saleable;
    }

    public void setSaleable(SaleableDTO saleable) {
        this.saleable = saleable;
    }

    public SaleablePackageDTO getSaleablePackage() {
        return saleablePackage;
    }

    public void setSaleablePackage(SaleablePackageDTO saleablePackage) {
        this.saleablePackage = saleablePackage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
