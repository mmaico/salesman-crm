package br.com.kproj.salesman.infrastructure.entity.saleable;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name="saleable")
@Inheritance(strategy=InheritanceType.JOINED)
public class SaleableUnit extends Identifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2422314617985187903L;

    @Id
    @GeneratedValue
    private Long id;

	@NotNull
    private String name;

    private String description;
    
    @NotNull
    private Boolean active = Boolean.TRUE;

    @NotNull
    @Min(value = 0, message = "saleable.price.must.be.greater.than.zero")
    @NumberFormat(pattern="###.###,##")
    private BigDecimal price;

    @NumberFormat(pattern="###.###,##")
    @Column(name = "price_cost", nullable = false)
    @Min(value = 0, message = "saleable.cost.price.must.be.greater.than.zero")
    private BigDecimal priceCost;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private SaleableType type;

    public SaleableUnit(){}
    public SaleableUnit(String name) {
        this.name = name;
    }
    public SaleableUnit(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
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

    public SaleableType getType() {
        return type;
    }

    public void setType(SaleableType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Saleable Unit {");
        sb.append("id=").append(getId());
        sb.append(", name=").append(name);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null) return false;
        SaleableUnit saleableUnit = (SaleableUnit) o;
        return Objects.equals(getId(), saleableUnit.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
