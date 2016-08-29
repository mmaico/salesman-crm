package br.com.kproj.salesman.negotiation2.domain.model.approval;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;


public class RequestApproval extends ModelIdentifiable {

    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
