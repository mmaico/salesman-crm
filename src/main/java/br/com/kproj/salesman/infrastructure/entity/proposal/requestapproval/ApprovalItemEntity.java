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
    @JoinColumn(name="approver_id")
    private ApproverEntity approverEntity;

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

    public ApproverEntity getApproverEntity() {
        return approverEntity;
    }

    public void setApproverEntity(ApproverEntity approverEntity) {
        this.approverEntity = approverEntity;
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
