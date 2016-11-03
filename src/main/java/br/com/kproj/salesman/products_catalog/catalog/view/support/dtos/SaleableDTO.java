package br.com.kproj.salesman.products_catalog.catalog.view.support.dtos;


import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;

public class SaleableDTO {

    private Long id;

    @NotEmpty(message = "saleable.name.cannotbe.null")
    private String name;
    private String description = StringUtils.EMPTY;
    private Boolean active = Boolean.FALSE;
    private BigDecimal price = BigDecimal.ZERO;
    private BigDecimal priceCost = BigDecimal.ZERO;
    private String type = StringUtils.EMPTY;
    private UnitDTO unit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceCost() {
        return priceCost;
    }

    public void setPriceCost(BigDecimal priceCost) {
        this.priceCost = priceCost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UnitDTO getUnit() {
        return unit;
    }

    public void setUnit(UnitDTO unit) {
        this.unit = unit;
    }
}
