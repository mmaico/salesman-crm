package br.com.kproj.salesman.products_catalog.domain.model.saleables;


import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Data
public class SaleableUnit extends ModelIdentifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2422314617985187903L;

    private Long id;

    private String name;

    private String description;
    
    @NotNull
    private Boolean active = Boolean.TRUE;

    private BigDecimal price;

    private BigDecimal priceCost;

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
