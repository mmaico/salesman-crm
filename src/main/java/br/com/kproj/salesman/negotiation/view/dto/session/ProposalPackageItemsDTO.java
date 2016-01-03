package br.com.kproj.salesman.negotiation.view.dto.session;


import java.io.Serializable;
import java.math.BigDecimal;

public class ProposalPackageItemsDTO implements Serializable {

    private Long id;
    private Long saleableId;
    private Integer quantity = 1;
    private BigDecimal price = BigDecimal.ZERO;
    private Boolean selected = Boolean.TRUE;

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

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProposalPackageItemsDTO that = (ProposalPackageItemsDTO) o;

        return saleableId != null ? saleableId.equals(that.saleableId) : that.saleableId == null;

    }

    @Override
    public int hashCode() {
        return saleableId != null ? saleableId.hashCode() : 0;
    }
}
