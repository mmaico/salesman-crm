package br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval;


import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.Approver;
import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;

public class PersonApprovalBuilder extends AbstractBuilder<PersonApproval>  {

	public PersonApprovalBuilder() {
		this.entity = new PersonApproval();
	}

	public PersonApprovalBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

    public PersonApprovalBuilder waitingStatus() {
        this.entity.setStatus(PersonApproval.Status.WAITING);
        return this;
    }

    public PersonApprovalBuilder withApprover(Approver approver) {
    	this.entity.setApprover(approver);
		return this;
	}

	public static PersonApprovalBuilder createPersonApproval(Long id) {
		return new PersonApprovalBuilder(id);
	}

	public static PersonApprovalBuilder createPersonApproval() {
		return new PersonApprovalBuilder();
	}
}
