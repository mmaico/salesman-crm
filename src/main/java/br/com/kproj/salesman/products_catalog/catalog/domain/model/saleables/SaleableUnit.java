package br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables;


import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Model
public class SaleableUnit extends ModelIdentifiable {


    private Long id;

    private String name;

    private String description;
    
    @NotNull
    private Boolean active = Boolean.TRUE;

    private BigDecimal price;

    private BigDecimal priceCost;

    private Represent represent;

    public SaleableUnit(){}

    public SaleableUnit(String name) {
        this.name = name;
    }
    public SaleableUnit(Long id) {
        this.id = id;
    }

    public Long getId(){
        return this.id;
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

    public Represent getRepresent() {
        return represent;
    }

    public void setRepresent(Represent represent) {
        this.represent = represent;
    }
}
