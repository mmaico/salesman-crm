package br.com.kproj.salesman.products_catalog.domain.model.saleables;

import com.google.common.collect.Lists;

import java.util.List;


public class SalePackage extends SaleableUnit {

    private List<SaleableUnit> saleables;


    public SalePackage(Long id) {
        super(id);
    }

    public SalePackage() {
        super();
    }

    public List<SaleableUnit> getSaleables() {
        return saleables;
    }

    public void setSaleables(List<SaleableUnit> saleables) {
        this.saleables = saleables;
    }

    public void addSaleable(SaleableUnit saleableUnit) {
        if (this.saleables == null) {
            this.saleables = Lists.newArrayList();
        }
        this.saleables.add(saleableUnit);
    }

    public void removeSaleable(SaleableUnit saleableUnit) {
        if (this.saleables == null) {
            return;
        }

        this.saleables.remove(saleableUnit);
    }
}
