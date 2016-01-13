package br.com.kproj.salesman.infrastructure.events.messages;


import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApproval;

public class RequestApprovalFinalizeMessage {


    private RequestApproval requestApproval;

    public RequestApprovalFinalizeMessage(RequestApproval requestApproval) {
        this.requestApproval = requestApproval;
    }

    public static RequestApprovalFinalizeMessage create(RequestApproval requestApproval) {
          return new RequestApprovalFinalizeMessage(requestApproval);
    }

    public RequestApproval getRequestApproval() {
        return requestApproval;
    }

    public void setRequestApproval(RequestApproval requestApproval) {
        this.requestApproval = requestApproval;
    }
}
