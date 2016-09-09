package br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.ApprovalItemEntity.StatusEntity;
import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="proposal_request_approval")
public class RequestApprovalEntity extends Identifiable {


    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="business_proposal_id")
    private BusinessProposalEntity proposal;

    @ManyToOne
    @JoinColumn(name="user_requester_id")
    private UserEntity userRequester;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "requestApproval")
    private List<ApprovalItemEntity> approvers;


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

    public List<ApprovalItemEntity> getApprovers() {
        return approvers;
    }

    public void setApprovers(List<ApprovalItemEntity> approvers) {
        this.approvers = approvers;
    }

    public Boolean isCompleted() {
        return approvers.stream()
                .filter(item -> StatusEntity.WAITING.equals(item.getStatus())).count() > 0;
    }
}
