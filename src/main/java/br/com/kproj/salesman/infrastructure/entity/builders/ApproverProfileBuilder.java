package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.ApproverProfile;

public class ApproverProfileBuilder extends AbstractBuilder<ApproverProfile>  {

	public ApproverProfileBuilder() {
		this.entity = new ApproverProfile();
	}

	public ApproverProfileBuilder(Long id) {
		this();
		this.entity.setId(id);
	}
	
    public ApproverProfileBuilder withApprover(User approver) {
        this.entity.setApprover(approver);
        return this;
    }
	public ApproverProfileBuilder withStatusAvaliable(Boolean status) {
		this.entity.setAvailable(status);
		return this;
	}

	public static ApproverProfileBuilder createProfile(Long id) {
		return new ApproverProfileBuilder(id);
	}

	public static ApproverProfileBuilder createProfile() {
		return new ApproverProfileBuilder();
	}
}
