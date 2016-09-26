package br.com.kproj.salesman.notifications2.approval.view.dtos;


import java.util.List;

public class RequestApprovalDTO {

    private NegotiationDTO negotiation;
    private List<PersonApprovalDTO> approvers;

    public NegotiationDTO getNegotiation() {
        return negotiation;
    }

    public void setNegotiation(NegotiationDTO negotiation) {
        this.negotiation = negotiation;
    }

    public List<PersonApprovalDTO> getApprovers() {
        return approvers;
    }

    public void setApprovers(List<PersonApprovalDTO> approvers) {
        this.approvers = approvers;
    }
}
