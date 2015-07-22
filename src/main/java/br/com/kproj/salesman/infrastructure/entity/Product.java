package br.com.kproj.salesman.infrastructure.entity;


import org.springframework.format.annotation.NumberFormat;

import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;
import java.util.Objects;

@MappedSuperclass
public abstract class Product extends Identifiable {

    private String name;

    private String description;

    private Boolean active;

    @NumberFormat(style= NumberFormat.Style.CURRENCY, pattern="#.###.##0,00")
    private BigDecimal price;

    @NumberFormat(style= NumberFormat.Style.CURRENCY, pattern="#,###.##")
    private BigDecimal priceCost;

    public Product(){}
    public Product(String name) {
        this.name = name;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(getId());
        sb.append(", name=").append(name);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
