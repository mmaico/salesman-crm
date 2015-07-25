package br.com.kproj.salesman.infrastructure.entity.proposal;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.Product;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name="proposal_product_item")
public class ProposalProductItem extends Identifiable {

    @ManyToOne
    @JoinColumn(name="product_id")
    @NotNull(message = "proposal.product.is.invalid")
    private Product product;

    @NotNull(message = "proposal.product.price.is.invalid")
    private BigDecimal price;

    private BigDecimal originalPrice;

    @NotNull
    @Min(value = 1, message = "quantity.product.lessthan.one")
    private Integer quantity = 0;

    @ManyToOne
    private BusinessProposal businessProposal;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
