package br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval;


import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.Approver;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.requester.Requester;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

import static br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.PersonApprovalBuilder.createPersonApproval;

public class RequestApproval extends ModelIdentifiable {


    public enum Status {
        APPROVED, WAITING, REJECTED
    }

    private Long id;

    private Status status = Status.WAITING;

    private Requester requester;

    private Negotiation negotiation;

    private List<PersonApproval> persons = Lists.newArrayList();



    public void startWithApprovers(Collection<Approver> approversAvailable) {
        approversAvailable.forEach(approver -> {
            PersonApproval approval = createPersonApproval()
                    .withApprover(approver).waitingStatus().build();
            this.persons.add(approval);
        });
    }






    //Getters and Setters

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Requester getRequester() {
        return requester;
    }

    public void setRequester(Requester requester) {
        this.requester = requester;
    }

    public Negotiation getNegotiation() {
        return negotiation;
    }

    public void setNegotiation(Negotiation negotiation) {
        this.negotiation = negotiation;
    }

    public List<PersonApproval> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonApproval> persons) {
        this.persons = persons;
    }



}
