package br.com.kproj.salesman.negotiation.domain.model.product;

import java.util.List;


public class SaleablePackage extends Saleable {

    private List<Saleable> saleables;


    public List<Saleable> getSaleables() {
        return saleables;
    }

    public void setSaleables(List<Saleable> saleables) {
        this.saleables = saleables;
    }
}
