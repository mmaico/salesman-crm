package br.com.kproj.salesman.products_catalog.view.support.builders;


import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;
import br.com.kproj.salesman.products_catalog.view.support.dtos.SaleableDTO;

public class SaleableMerge {


    public static void merge(SaleableDTO saleableDTO, SaleableUnit saleable) {
        saleable.setId(saleableDTO.getId());
        saleable.setName(saleableDTO.getName());
        saleable.setDescription(saleableDTO.getDescription());
        saleable.setActive(saleableDTO.getActive());
        saleable.setPrice(saleableDTO.getPrice());
        saleable.setPriceCost(saleableDTO.getPriceCost());
    }
}
