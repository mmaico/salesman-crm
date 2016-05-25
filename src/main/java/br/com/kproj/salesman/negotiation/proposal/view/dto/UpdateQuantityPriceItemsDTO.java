package br.com.kproj.salesman.negotiation.proposal.view.dto;


import java.io.Serializable;
import java.math.BigDecimal;

public class UpdateQuantityPriceItemsDTO implements Serializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UpdateQuantityPriceItemsDTO that = (UpdateQuantityPriceItemsDTO) o;

        return saleableId != null ? saleableId.equals(that.saleableId) : that.saleableId == null;

    }

    @Override
    public int hashCode() {
        return saleableId != null ? saleableId.hashCode() : 0;
    }
}
