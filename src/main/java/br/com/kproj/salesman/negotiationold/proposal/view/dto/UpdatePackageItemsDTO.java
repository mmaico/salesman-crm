package br.com.kproj.salesman.negotiationold.proposal.view.dto;


import br.com.kproj.salesman.negotiationold.proposal.view.dto.session.ProposalPackageItemsDTO;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class UpdatePackageItemsDTO implements Serializable {

    private Long saleableId;
    private Integer quantity = 1;
    private BigDecimal price = BigDecimal.ZERO;

    private List<ProposalPackageItemsDTO> packageItems = Lists.newArrayList();


    public List<ProposalPackageItemsDTO> getPackageItems() {
        return packageItems;
    }

    public void setPackageItems(List<ProposalPackageItemsDTO> packageItems) {
        this.packageItems = packageItems;
    }

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

        UpdatePackageItemsDTO that = (UpdatePackageItemsDTO) o;

        return saleableId != null ? saleableId.equals(that.saleableId) : that.saleableId == null;

    }

    @Override
    public int hashCode() {
        return saleableId != null ? saleableId.hashCode() : 0;
    }
}
