package br.com.kproj.salesman.negotiation2.domain.model.product;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;


public class SaleablePackage extends ModelIdentifiable {

    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
