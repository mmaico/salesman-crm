package br.com.kproj.salesman.infrastructure.events.messages;


import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApprovalEntity;

@Deprecated
public class RequestNewApprovalMessage {


    private RequestApprovalEntity requestApprovalEntity;

    public RequestNewApprovalMessage(RequestApprovalEntity requestApprovalEntity) {
        this.requestApprovalEntity = requestApprovalEntity;
    }

    public static RequestNewApprovalMessage create(RequestApprovalEntity requestApprovalEntity) {
          return new RequestNewApprovalMessage(requestApprovalEntity);
    }

    public RequestApprovalEntity getRequestApprovalEntity() {
        return requestApprovalEntity;
    }

    public void setRequestApprovalEntity(RequestApprovalEntity requestApprovalEntity) {
        this.requestApprovalEntity = requestApprovalEntity;
    }
}
