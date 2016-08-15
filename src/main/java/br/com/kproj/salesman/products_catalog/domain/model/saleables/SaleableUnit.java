package br.com.kproj.salesman.products_catalog.domain.model.saleables;


import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

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


}
