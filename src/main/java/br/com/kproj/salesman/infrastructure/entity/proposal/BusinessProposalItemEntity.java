package br.com.kproj.salesman.infrastructure.entity.proposal;


import br.com.kproj.salesman.infrastructure.configuration.ExcludeField;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name="business_proposal_item")
public class BusinessProposalItemEntity extends Identifiable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3881704814612452364L;

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "domain.saleable.price.is.invalid")
    private BigDecimal price;

    @NotNull(message = "domain.saleable.original.price.is.invalid")
    @Column(name = "original_price")
    private BigDecimal originalPrice;

    @NotNull
    @Min(value = 1, message = "quantity.saleable.lessthan.one")
    private Integer quantity = 0;

    @ManyToOne
    @JoinColumn(name="business_proposal_id")
    @ExcludeField
    private BusinessProposalEntity businessProposalEntity;

    public BusinessProposalItemEntity() {}

    public BusinessProposalItemEntity(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BusinessProposalEntity getBusinessProposalEntity() {
        return businessProposalEntity;
    }

    public void setBusinessProposalEntity(BusinessProposalEntity businessProposalEntity) {
        this.businessProposalEntity = businessProposalEntity;
    }

}
