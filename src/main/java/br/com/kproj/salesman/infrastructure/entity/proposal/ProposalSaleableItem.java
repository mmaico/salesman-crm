package br.com.kproj.salesman.infrastructure.entity.proposal;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.saleable.Package;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name="proposal_saleable_item")
public class ProposalSaleableItem extends Identifiable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3881704814612452364L;

    @Id
    @GeneratedValue
    private Long id;

	@ManyToOne
    @JoinColumn(name="saleable_id")
    @NotNull(message = "proposal.saleable.is.invalid")
    private SaleableUnit saleableUnit;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private Package packageSaleable;

    @NotNull(message = "proposal.saleable.price.is.invalid")
    private BigDecimal price;

    private BigDecimal originalPrice;

    @NotNull
    @Min(value = 1, message = "quantity.saleable.lessthan.one")
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

    public Package getPackageSaleable() {
        return packageSaleable;
    }

    public void setPackageSaleable(Package packageSaleable) {
        this.packageSaleable = packageSaleable;
    }

    public BusinessProposal getBusinessProposal() {
        return businessProposal;
    }

    public void setBusinessProposal(BusinessProposal businessProposal) {
        this.businessProposal = businessProposal;
    }
}
