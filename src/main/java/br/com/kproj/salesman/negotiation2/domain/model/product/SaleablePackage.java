package br.com.kproj.salesman.negotiation2.domain.model.product;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;

import java.util.List;


public class SaleablePackage extends ModelIdentifiable {

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
