package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.ApproverStatus;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.Approver;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApprovalEntity;

public class ApproverBuilder extends AbstractBuilder<Approver>  {

	public ApproverBuilder() {
		this.entity = new Approver();
	}

	public ApproverBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

    public ApproverBuilder withProposal(RequestApprovalEntity request) {
        this.entity.setRequestApprovalEntity(request);
        return this;
    }

    public ApproverBuilder withStatus(ApproverStatus status) {
        this.entity.setStatus(status);
        return this;
    }

    public ApproverBuilder withApprover(UserEntity approver) {
        this.entity.setApprover(approver);
        return this;
    }

    public ApproverBuilder withDescription(String description) {
        this.entity.setDescription(description);
        return this;
    }


	public static ApproverBuilder createApprover(Long id) {
		return new ApproverBuilder(id);
	}

	public static ApproverBuilder createApprover() {
		return new ApproverBuilder();
	}
}
