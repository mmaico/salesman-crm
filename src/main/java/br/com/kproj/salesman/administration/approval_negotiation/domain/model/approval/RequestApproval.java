package br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval;


import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;

public class RequestApproval extends ModelIdentifiable {

    public enum RequestApprovalStatus {
        APPROVED, WAITING, REJECTED
    }

    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
