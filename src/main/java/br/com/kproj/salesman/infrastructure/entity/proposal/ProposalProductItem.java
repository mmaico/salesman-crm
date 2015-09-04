package br.com.kproj.salesman.infrastructure.entity.proposal;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name="proposal_product_item")
public class ProposalProductItem extends Identifiable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3881704814612452364L;

    @Id
    @GeneratedValue
    private Long id;

	@ManyToOne
    @JoinColumn(name="product_id")
    @NotNull(message = "proposal.product.is.invalid")
    private SaleableUnit saleableUnit;

    @NotNull(message = "proposal.product.price.is.invalid")
    private BigDecimal price;

    private BigDecimal originalPrice;

    @NotNull
    @Min(value = 1, message = "quantity.product.lessthan.one")
    private Integer quantity = 0;

    @ManyToOne
    @JoinColumn(name="business_proposal_id")
    private BusinessProposal businessProposal;

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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
