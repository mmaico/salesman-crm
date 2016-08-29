package br.com.kproj.salesman.negotiation.view.dto.session;


import br.com.kproj.salesman.negotiation.domain.model.negotiation.SaleableItem;
import br.com.kproj.salesman.negotiation.domain.model.product.Saleable;
import br.com.kproj.salesman.negotiation.domain.model.product.SaleablePackage;
import br.com.kproj.salesman.negotiation.domain.model.product.SaleableRepository;
import br.com.kproj.salesman.negotiation.view.dto.UpdatePackageItemsDTO;
import br.com.kproj.salesman.negotiation.view.dto.UpdateQuantityPriceItemsDTO;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value="session")
public class ProposalSaleablesDTO implements Serializable {

    @Autowired
    private SaleableRepository repository;

    private Long negotiationId;

    private Set<ProposalSaleableItemDTO> proposalSaleableItemDTOs = Sets.newHashSet();

    public Set<ProposalSaleableItemDTO> getProposalSaleableItemDTOs() {
        return proposalSaleableItemDTOs;
    }

    public void setProposalSaleableItemDTOs(Set<ProposalSaleableItemDTO> proposalSaleableItemDTOs) {
        this.proposalSaleableItemDTOs = proposalSaleableItemDTOs;
    }

    public void add(Saleable saleableUnit) {
        Optional<Saleable> result = repository.findOne(saleableUnit.getId());

        if (result.isPresent()) {
            ProposalSaleableItemDTO proposalSaleableItemDTO = new ProposalSaleableItemDTO();
            proposalSaleableItemDTO.setSaleableId(result.get().getId());
            proposalSaleableItemDTO.setQuantity(1);

            if (result.get() instanceof SaleablePackage) {
                SaleablePackage salePackage = (SaleablePackage) result.get();
                salePackage.getSaleables()
                        .forEach(saleunit ->
                                proposalSaleableItemDTO.addPackageItemDTO(saleunit.getId(), 1, saleunit.getPrice()));
            }

            proposalSaleableItemDTOs.add(proposalSaleableItemDTO);
        }
    }

    public void mergeItems(UpdatePackageItemsDTO item) {
        Optional<Saleable> result = repository.findOne(item.getSaleableId());

        if (result.isPresent()) {

            if (result.get() instanceof SaleablePackage) {
                Optional<ProposalSaleableItemDTO> packageFound = proposalSaleableItemDTOs.stream()
                        .filter(spackage -> item.getSaleableId().equals(spackage.getSaleableId())).findFirst();

                if (!packageFound.isPresent()) {
                    return;
                }
                item.getPackageItems().forEach(itemToMerge -> packageFound.get().updateItem(itemToMerge));
            }
        }
    }

    public void updateRootItem(UpdateQuantityPriceItemsDTO dto) {
        Optional<Saleable> result = repository.findOne(dto.getSaleableId());

        if(result.isPresent()) {
            Optional<ProposalSaleableItemDTO> itemFound = proposalSaleableItemDTOs.stream().filter(item ->
                    item.getSaleableId().equals(dto.getSaleableId())).findFirst();

            if(itemFound.isPresent()) {
               itemFound.get().updateRootItem(dto);
            }
        }
    }

    public void deleteRootItem(Long saleableId) {
        this.proposalSaleableItemDTOs.remove(new ProposalSaleableItemDTO(saleableId));
    }

    public Optional<ProposalSaleableItemDTO> getByPackageId(Long packageId) {
        return this.proposalSaleableItemDTOs.stream().filter(dto -> packageId.equals(dto.getSaleableId())).findFirst();
    }

    /**
     * Usado para carregar os produtos de uma compra que ja esta salva
     *
     */
    public void load(List<SaleableItem> saleableItems) {

        List<SaleableItem> onlyPackages = saleableItems.stream()
                    .filter(filterPackage -> filterPackage.hasPackage()).collect(Collectors.toList());

        onlyPackages.stream().forEach(itemPackage ->
                proposalSaleableItemDTOs.add(ProposalSaleableItemDTO.create(itemPackage)));

        for (SaleableItem item: saleableItems) {
            if (item.hasPackage()) continue;

            if (item.hasSaleableWithPackage()) {
                Optional<ProposalSaleableItemDTO> result = proposalSaleableItemDTOs.stream()
                        .filter(packageItem -> packageItem.getSaleableId().equals(item.getSaleablePackage().getId())).findFirst();
                result.get().addPackageItemDTO(item.getId(), item.getSaleable().getId(), item.getQuantity(), item.getPrice());
            } else {
                this.proposalSaleableItemDTOs.add(ProposalSaleableItemDTO.create(item));
            }
        }

    }

    public void setNegotiationId(Long proposalId) {
        this.negotiationId = proposalId;
    }

    public Long getNegotiationId() {
        return negotiationId;
    }

    public void clear() {
        this.negotiationId = null;
        this.proposalSaleableItemDTOs.clear();
    }



}
