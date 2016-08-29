package br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.ApproverStatus;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="proposal_request_approval")
public class RequestApprovalEntity extends Identifiable {


    public enum RequestApprovalStatus {
        APPROVED, WAITING, DISAPPROVED
    }

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="business_proposal_id")
    private BusinessProposalEntity proposal;

    @ManyToOne
    @JoinColumn(name="user_requester_id")
    private UserEntity userRequester;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "requestApprovalEntity")
    private List<Approver> approvers = Lists.newArrayList();

    @Enumerated(EnumType.STRING)
    private RequestApprovalStatus status;


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BusinessProposalEntity getProposal() {
        return proposal;
    }

    public void setProposal(BusinessProposalEntity proposal) {
        this.proposal = proposal;
    }

    public UserEntity getUserRequester() {
        return userRequester;
    }

    public void setUserRequester(UserEntity userRequester) {
        this.userRequester = userRequester;
    }

    public List<Approver> getApprovers() {
        return approvers;
    }

    public void setApprovers(List<Approver> approvers) {
        this.approvers = approvers;
    }

    public RequestApprovalStatus getStatus() {
        return status;
    }

    public void setStatus(RequestApprovalStatus status) {
        this.status = status;
    }

    public void addApprover(Approver approver) {
        if (approvers == null) {
            approvers = Lists.newArrayList();
        }
        approvers.add(approver);
    }
    
    public void setCurrentStatus() {
        long count = approvers.stream()
                .filter(approver -> ApproverStatus.DISAPPROVED == approver.getStatus()).count();
        
        if (count > 0) {
            this.status =  RequestApprovalStatus.DISAPPROVED;
            return;
        }

        long stillLackAvaluation = approvers.stream().filter(approver -> ApproverStatus.WAITING == approver.getStatus()).count();

        if (stillLackAvaluation > 0) {
            this.status =  RequestApprovalStatus.WAITING;
            return;
        }

        this.status = RequestApprovalStatus.APPROVED;
    }
}
