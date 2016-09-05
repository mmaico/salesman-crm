package br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval;


import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.Approver;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;

public class PersonApproval extends ModelIdentifiable {

    public enum Status {
        APPROVED, WAITING, REJECTED
    }

    private Long id;

    private Approver approver;

    private Status status;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Approver getApprover() {
        return approver;
    }

    public void setApprover(Approver approver) {
        this.approver = approver;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
