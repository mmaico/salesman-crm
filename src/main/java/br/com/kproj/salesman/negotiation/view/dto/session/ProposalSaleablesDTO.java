package br.com.kproj.salesman.negotiation.view.dto.session;


import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalSaleableItem;
import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackage;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableType;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.negotiation.view.dto.UpdatePackageItemsDTO;
import br.com.kproj.salesman.negotiation.view.dto.UpdateQuantityPriceItemsDTO;
import br.com.kproj.salesman.register.application.contract.saleable.SaleableApplication;
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
import java.util.stream.Stream;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value="session")
public class ProposalSaleablesDTO implements Serializable {

    @Autowired
    private SaleableApplication application;

    private Set<ProposalSaleableItemDTO> proposalSaleableItemDTOs = Sets.newHashSet();

    public Set<ProposalSaleableItemDTO> getProposalSaleableItemDTOs() {
        return proposalSaleableItemDTOs;
    }

    public void setProposalSaleableItemDTOs(Set<ProposalSaleableItemDTO> proposalSaleableItemDTOs) {
        this.proposalSaleableItemDTOs = proposalSaleableItemDTOs;
    }

    public void add(SaleableUnit saleableUnit) {
        Optional<SaleableUnit> result = application.getOne(saleableUnit.getId());

        if (result.isPresent()) {
            ProposalSaleableItemDTO proposalSaleableItemDTO = new ProposalSaleableItemDTO();
            proposalSaleableItemDTO.setSaleableId(result.get().getId());
            proposalSaleableItemDTO.setPrice(result.get().getPrice());
            proposalSaleableItemDTO.setQuantity(1);

            if (SaleableType.PACKAGE.equals(result.get().getType())) {
                SalePackage salePackage = (SalePackage) result.get();
                salePackage.getSaleableUnits()
                        .forEach(saleunit ->
                                proposalSaleableItemDTO.addPackageItemDTO(saleunit.getId(), 1, saleunit.getPrice()));
            }

            proposalSaleableItemDTOs.add(proposalSaleableItemDTO);
        }
    }

    public void mergeItems(UpdatePackageItemsDTO item) {

        Optional<SaleableUnit> result = application.getOne(item.getSaleableId());
        if (result.isPresent()) {

            if (SaleableType.PACKAGE.equals(result.get().getType())) {
                Optional<ProposalSaleableItemDTO> packageFound = proposalSaleableItemDTOs.stream().filter(spackage -> item.getSaleableId()
                        .equals(spackage.getSaleableId())).findFirst();

                if (!packageFound.isPresent()) {
                    return;
                }
                item.getPackageItems().forEach(itemToMerge -> packageFound.get().updateItem(itemToMerge));
            }
        }
    }

    public void updateRootItem(UpdateQuantityPriceItemsDTO dto) {
        Optional<SaleableUnit> result = application.getOne(dto.getSaleableId());

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
    public void load(List<ProposalSaleableItem> saleableItems) {

        List<ProposalSaleableItem> onlyPackages = saleableItems.stream()
                    .filter(filterPackage -> filterPackage.hasPackage()).collect(Collectors.toList());

        onlyPackages.stream().forEach(itemPackage ->
                proposalSaleableItemDTOs.add(ProposalSaleableItemDTO.create(itemPackage)));


        for (ProposalSaleableItem item: saleableItems) {
            if (item.hasPackage()) continue;
            if (item.hasSaleableWithPackage()) {
                Optional<ProposalSaleableItemDTO> result = proposalSaleableItemDTOs.stream()
                        .filter(packageItem -> packageItem.getSaleableId().equals(item.getSalePackage().getId())).findFirst();
                result.get().addPackageItemDTO(item.getId(), item.getSaleableUnit().getId(), item.getQuantity(), item.getPrice());
            } else {
                this.proposalSaleableItemDTOs.add(ProposalSaleableItemDTO.create(item));
            }
        }

    }


    public void clear() {
        this.proposalSaleableItemDTOs.clear();
    }
}
