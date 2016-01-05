package br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="proposal_request_approval")
public class RequestApproval extends Identifiable {


    enum RequestApprovalStatus {
        APPROVED, WAITING, DISAPPROVED
    }

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="business_proposal_id")
    private BusinessProposal proposal;

    @ManyToOne
    @JoinColumn(name="user_requester_id")
    private User userRequester;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "requestApproval")
    private List<Approver> approvers;

    @Enumerated(EnumType.STRING)
    private RequestApprovalStatus status;


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BusinessProposal getProposal() {
        return proposal;
    }

    public void setProposal(BusinessProposal proposal) {
        this.proposal = proposal;
    }

    public User getUserRequester() {
        return userRequester;
    }

    public void setUserRequester(User userRequester) {
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
}
