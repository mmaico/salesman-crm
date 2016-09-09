package br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval;


import br.com.kproj.salesman.administration.approval_negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.requester.Requester;
import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;

public class RequestApprovalBuilder extends AbstractBuilder<RequestApproval>  {

	public RequestApprovalBuilder() {
		this.entity = new RequestApproval();
	}

	public RequestApprovalBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

    public RequestApprovalBuilder withNegotiationId(Long negotiationId) {
    	this.entity.setNegotiation(new Negotiation(negotiationId));
		return this;
	}

    public RequestApprovalBuilder withRequester(Long requesterId) {
    	this.entity.setRequester(new Requester(requesterId));
		return this;
	}

	public static RequestApprovalBuilder createRequestApproval() {
		return new RequestApprovalBuilder();
	}
}
