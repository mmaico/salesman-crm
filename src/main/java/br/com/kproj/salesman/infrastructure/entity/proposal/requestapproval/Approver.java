package br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.ApproverStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="proposal_approver")
public class Approver extends Identifiable {


    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_approver_id")
    @NotNull(message = "approver.user.cannot.be.null")
    private UserEntity approver;

    private String description;

    @Enumerated(EnumType.STRING)
    private ApproverStatus status;

    @ManyToOne
    @JoinColumn(name="request_approval_id")
    private RequestApprovalEntity requestApprovalEntity;


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getApprover() {
        return approver;
    }

    public void setApprover(UserEntity approver) {
        this.approver = approver;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ApproverStatus getStatus() {
        return status;
    }

    public void setStatus(ApproverStatus status) {
        this.status = status;
    }

    public RequestApprovalEntity getRequestApprovalEntity() {
        return requestApprovalEntity;
    }

    public void setRequestApprovalEntity(RequestApprovalEntity requestApprovalEntity) {
        this.requestApprovalEntity = requestApprovalEntity;
    }


}
