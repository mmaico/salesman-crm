package br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;

public class ApproverBuilder extends AbstractBuilder<Approver>  {

	public ApproverBuilder() {
		this.entity = new Approver();
	}

	public ApproverBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

    public ApproverBuilder isAvailable(Boolean available) {
        this.entity.setAvailable(available);
        return this;
    }

	public static ApproverBuilder createApprover(Long id) {
		return new ApproverBuilder(id);
	}

	public static ApproverBuilder createApprover() {
		return new ApproverBuilder();
	}
}
