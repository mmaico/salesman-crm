package br.com.kproj.salesman.negotiation.view.dto.session;


import java.io.Serializable;
import java.math.BigDecimal;

public class ProposalPackageItemsDTO implements Serializable {

    private Long saleableId;
    private Integer quantity = 1;
    private BigDecimal price = BigDecimal.ZERO;

    public Long getSaleableId() {
        return saleableId;
    }

    public void setSaleableId(Long saleableId) {
        this.saleableId = saleableId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
