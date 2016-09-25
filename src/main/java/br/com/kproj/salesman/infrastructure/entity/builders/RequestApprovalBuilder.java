package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApprovalEntity;

public class RequestApprovalBuilder extends AbstractBuilder<RequestApprovalEntity>  {

	public RequestApprovalBuilder() {
		this.entity = new RequestApprovalEntity();
	}

	public RequestApprovalBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

    public RequestApprovalBuilder withProposal(BusinessProposalEntity proposal) {
        this.entity.setProposal(proposal);
        return this;
    }

    public RequestApprovalBuilder withUserRequester(UserEntity userRequester) {
        this.entity.setUserRequester(userRequester);
        return this;
    }

//    public RequestApprovalBuilder withStatus(RequestApprovalEntity.RequestApprovalStatus status) {
//        this.entity.setStatus(status);
//        return this;
//    }

//    public RequestApprovalBuilder withApprovers(List<Approver> approval) {
//        this.entity.setApprovers(approval);
//        return this;
//    }


	public static RequestApprovalBuilder createRequestApproval(Long id) {
		return new RequestApprovalBuilder(id);
	}

	public static RequestApprovalBuilder createRequestApproval() {
		return new RequestApprovalBuilder();
	}
}
