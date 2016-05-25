package br.com.kproj.salesman.negotiation.proposal.view.dto.session;


import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalSaleableItem;
import br.com.kproj.salesman.negotiation.proposal.view.dto.UpdateQuantityPriceItemsDTO;
import com.google.common.collect.Sets;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import static br.com.kproj.salesman.infrastructure.helpers.NumberHelper.isNegativeNumber;

public class ProposalSaleableItemDTO implements Serializable {
    private Long id;
    private Long saleableId;
    private Integer quantity = 1;
    private BigDecimal price = BigDecimal.ZERO;

    private Set<ProposalPackageItemsDTO> packageItems = Sets.newHashSet();

    public ProposalSaleableItemDTO(Long saleableId) {
        this.saleableId = saleableId;
    }
    public ProposalSaleableItemDTO() {}

    public Set<ProposalPackageItemsDTO> getPackageItems() {
        return packageItems;
    }

    public void setPackageItems(Set<ProposalPackageItemsDTO> packageItems) {
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

    public void addPackageItemDTO(Long saleableId, Integer quantity, BigDecimal price) {
        ProposalPackageItemsDTO item = new ProposalPackageItemsDTO();
        item.setPrice(price);
        item.setQuantity(quantity);
        item.setSaleableId(saleableId);
        item.setSelected(Boolean.TRUE);

        packageItems.add(item);
    }

    public void addPackageItemDTO(Long id, Long saleableId, Integer quantity, BigDecimal price) {
        ProposalPackageItemsDTO item = new ProposalPackageItemsDTO();
        item.setId(id);
        item.setPrice(price);
        item.setQuantity(quantity);
        item.setSaleableId(saleableId);
        item.setSelected(Boolean.TRUE);

        packageItems.add(item);
    }

    public void updateItem(ProposalPackageItemsDTO item) {
        if (packageItems.contains(item)) {
            Optional<ProposalPackageItemsDTO> itemSelected = packageItems.stream()
                    .filter(itemFiltered -> itemFiltered.equals(item)).findFirst();

            itemSelected.get().setPrice(item.getPrice());
            itemSelected.get().setQuantity(item.getQuantity());
            itemSelected.get().setSelected(item.getSelected() == null ? Boolean.FALSE : item.getSelected());
        } else {
            ProposalPackageItemsDTO newItem = new ProposalPackageItemsDTO();
            newItem.setSaleableId(item.getSaleableId());
            newItem.setPrice(item.getPrice());
            newItem.setQuantity(item.getQuantity());
            newItem.setSelected(item.getSelected() == null ? Boolean.FALSE : item.getSelected());

            packageItems.add(newItem);
        }
    }

    public void updateRootItem(UpdateQuantityPriceItemsDTO item) {

        this.price = !isNegativeNumber(item.getPrice()) ? item.getPrice(): this.price;
        this.quantity = item.getQuantity() >= 0 ? item.getQuantity() : this.quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProposalPackageItemsDTO getById(Long saleableId) {

        Optional<ProposalPackageItemsDTO> result = packageItems.stream().filter(item -> item.getSaleableId().equals(saleableId)).findFirst();

        return result.isPresent() ? result.get() : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProposalSaleableItemDTO that = (ProposalSaleableItemDTO) o;

        return saleableId != null ? saleableId.equals(that.saleableId) : that.saleableId == null;

    }

    public static ProposalSaleableItemDTO create(ProposalSaleableItem item) {
        ProposalSaleableItemDTO dto = new ProposalSaleableItemDTO();
        dto.setId(item.getId());
        dto.setPrice(item.getPrice());
        dto.setQuantity(item.getQuantity());

        if (item.hasPackage()) {
            dto.setSaleableId(item.getSalePackage().getId());
        } else {
            dto.setSaleableId(item.getSaleableUnit().getId());
        }

        return dto;
    }

    @Override
    public int hashCode() {
        return saleableId != null ? saleableId.hashCode() : 0;
    }
}
