package br.com.kproj.salesman.negotiation.domain.model.operation;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;


public class Region extends ModelIdentifiable {

    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
