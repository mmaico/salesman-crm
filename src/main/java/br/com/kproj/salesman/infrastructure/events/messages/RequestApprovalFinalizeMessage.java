package br.com.kproj.salesman.infrastructure.events.messages;


import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApprovalEntity;

public class RequestApprovalFinalizeMessage {


    private RequestApprovalEntity requestApprovalEntity;

    public RequestApprovalFinalizeMessage(RequestApprovalEntity requestApprovalEntity) {
        this.requestApprovalEntity = requestApprovalEntity;
    }

    public static RequestApprovalFinalizeMessage create(RequestApprovalEntity requestApprovalEntity) {
          return new RequestApprovalFinalizeMessage(requestApprovalEntity);
    }

    public RequestApprovalEntity getRequestApprovalEntity() {
        return requestApprovalEntity;
    }

    public void setRequestApprovalEntity(RequestApprovalEntity requestApprovalEntity) {
        this.requestApprovalEntity = requestApprovalEntity;
    }
}
