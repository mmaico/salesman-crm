package br.com.kproj.salesman.administration.approval_negotiation.domain.model.requester;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;


public class Requester extends ModelIdentifiable {

    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
