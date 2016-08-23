package br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver;


import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;

public class Approver extends ModelIdentifiable {

    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
