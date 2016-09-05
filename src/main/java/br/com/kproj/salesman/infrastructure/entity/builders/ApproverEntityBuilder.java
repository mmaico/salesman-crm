package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.ApproverEntity;

public class ApproverEntityBuilder extends AbstractBuilder<ApproverEntity>  {

	public ApproverEntityBuilder() {
		this.entity = new ApproverEntity();
	}

	public ApproverEntityBuilder(Long id) {
		this();
		this.entity.setId(id);
	}
	
    public ApproverEntityBuilder withApprover(UserEntity approver) {
        this.entity.setApprover(approver);
        return this;
    }
	public ApproverEntityBuilder withStatusAvaliable(Boolean status) {
		this.entity.setAvailable(status);
		return this;
	}

	public static ApproverEntityBuilder createProfile(Long id) {
		return new ApproverEntityBuilder(id);
	}

	public static ApproverEntityBuilder createProfile() {
		return new ApproverEntityBuilder();
	}
}
