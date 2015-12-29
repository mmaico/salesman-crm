package br.com.kproj.salesman.negotiation.view.dto.session;


import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackage;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableType;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.register.application.contract.saleable.SaleableApplication;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

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

    public void mergeItems(ProposalSaleableItemDTO item) {

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

    public Optional<ProposalSaleableItemDTO> getByPackageId(Long packageId) {
        return this.proposalSaleableItemDTOs.stream().filter(dto -> packageId.equals(dto.getSaleableId())).findFirst();
    }

    public void clear() {
        this.proposalSaleableItemDTOs.clear();
    }
}
