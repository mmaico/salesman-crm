package br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.User;
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
    private User approver;

    private String description;

    @Enumerated(EnumType.STRING)
    private ApproverStatus status;

    @ManyToOne
    @JoinColumn(name="request_approval_id")
    private RequestApproval requestApproval;


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getApprover() {
        return approver;
    }

    public void setApprover(User approver) {
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

    public RequestApproval getRequestApproval() {
        return requestApproval;
    }

    public void setRequestApproval(RequestApproval requestApproval) {
        this.requestApproval = requestApproval;
    }


}
