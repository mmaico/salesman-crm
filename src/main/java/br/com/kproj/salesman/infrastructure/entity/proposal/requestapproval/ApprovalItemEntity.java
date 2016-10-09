package br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;

import javax.persistence.*;

@Entity
@Table(name = "approval_item")
public class ApprovalItemEntity extends Identifiable {

    public enum StatusEntity {
        APPROVED, WAITING, DISAPPROVED
    }

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_approver_id")
    private ApproverEntity approver;

    @Enumerated(EnumType.STRING)
    private StatusEntity status;

    @ManyToOne
    @JoinColumn(name="request_approval_id")
    private RequestApprovalEntity requestApproval;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApproverEntity getApprover() {
        return approver;
    }

    public void setApprover(ApproverEntity approver) {
        this.approver = approver;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }

    public RequestApprovalEntity getRequestApproval() {
        return requestApproval;
    }

    public void setRequestApproval(RequestApprovalEntity requestApproval) {
        this.requestApproval = requestApproval;
    }
}
