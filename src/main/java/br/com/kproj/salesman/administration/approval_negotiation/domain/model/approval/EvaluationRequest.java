package br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval;

import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.Approver;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.infrastructure.model.ValueObject;


public class EvaluationRequest implements ValueObject {

    private final Negotiation negotiation;
    private final Approver approver;
    private final PersonApproval.Status status;

    public EvaluationRequest(Negotiation negotiation, Approver approver, PersonApproval.Status status) {
        this.negotiation = negotiation;
        this.approver = approver;
        this.status = status;
    }


    public static EvaluationRequest createEvaluation(Negotiation negotiation, Approver approver, PersonApproval.Status status) {
        return new EvaluationRequest(negotiation, approver, status);
    }

    public Negotiation getNegotiation() {
        return negotiation;
    }

    public Approver getApprover() {
        return approver;
    }

    public PersonApproval.Status getStatus() {
        return status;
    }
}
