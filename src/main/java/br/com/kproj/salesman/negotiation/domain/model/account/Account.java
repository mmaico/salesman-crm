package br.com.kproj.salesman.negotiation.domain.model.account;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;


public class Account extends ModelIdentifiable {

    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
