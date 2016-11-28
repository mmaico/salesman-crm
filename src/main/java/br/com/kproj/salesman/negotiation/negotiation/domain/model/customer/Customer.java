package br.com.kproj.salesman.negotiation.negotiation.domain.model.customer;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;


public class Customer extends ModelIdentifiable {

    private Long id;

    public Customer(){}

    public Customer(Long id){
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
