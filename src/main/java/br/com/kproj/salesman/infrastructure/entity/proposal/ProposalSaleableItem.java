package br.com.kproj.salesman.infrastructure.entity.proposal;


import br.com.kproj.salesman.auditing.infrastructure.ExcludeAuditingField;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackageEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;

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
    @ExcludeAuditingField
    private SaleableUnitEntity saleableUnit;

    @ManyToOne
    @JoinColumn(name = "package_id")
    @ExcludeAuditingField
    private SalePackageEntity salePackage;

    @NotNull(message = "domain.saleable.price.is.invalid")
    private BigDecimal price;

    @NotNull(message = "domain.saleable.original.price.is.invalid")
    private BigDecimal originalPrice;

    @NotNull
    @Min(value = 1, message = "quantity.saleable.lessthan.one")
    private Integer quantity = 0;

    @ManyToOne
    @JoinColumn(name="business_proposal_id")
    @ExcludeAuditingField
    private BusinessProposal businessProposal;




    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public SalePackageEntity getSalePackage() {
        return salePackage;
    }

    public void setSalePackage(SalePackageEntity salePackage) {
        this.salePackage = salePackage;
    }

    public BusinessProposal getBusinessProposal() {
        return businessProposal;
    }

    public void setBusinessProposal(BusinessProposal businessProposal) {
        this.businessProposal = businessProposal;
    }

    public Boolean hasPackage() {
        return saleableUnit == null && salePackage != null;
    }

    public Boolean hasSaleableWithPackage() {
        return saleableUnit != null && salePackage != null;
    }
}
