package br.com.kproj.salesman.negotiation.domain.model.product;

import java.util.List;


public class SaleablePackage extends Saleable {

    private Long id;

    private List<Saleable> saleables;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Saleable> getSaleables() {
        return saleables;
    }

    public void setSaleables(List<Saleable> saleables) {
        this.saleables = saleables;
    }
}
