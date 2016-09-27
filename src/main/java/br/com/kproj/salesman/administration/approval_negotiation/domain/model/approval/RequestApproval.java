package br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval;


import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.Approver;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.requester.Requester;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Attribute;
import com.trex.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Optional;

import static br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.PersonApprovalBuilder.createPersonApproval;
import static br.com.kproj.salesman.infrastructure.helpers.AutowireHelper.autowire;

@Model
public class RequestApproval extends ModelIdentifiable {

    private Long id;

    @Attribute(destinationName = "userRequester")
    private Requester requester;

    @Attribute(destinationName = "proposal")
    private Negotiation negotiation;

    private Approvers approvers = new Approvers();

    @Autowired
    private PersonApprovalRepository personRepository;

    public RequestApproval() {
        autowire(this);
    }


    public void startWithApprovers(Collection<Approver> approversAvailable) {
        approversAvailable.forEach(approver -> {
            PersonApproval approval = createPersonApproval()
                    .withApprover(approver).waitingStatus().build();
            this.approvers.add(approval);
        });
    }

    public Boolean stillNeedsToBeEvaluationBy(Approver approver) {
        return approvers.needsToBeEvaluationBy(approver);
    }

    public void makeEvaluation(Approver approver, PersonApproval.Status status) {
        Optional<PersonApproval> personApproval = approvers.getOne(approver);

        personApproval.get().setStatus(status);
        personRepository.save(personApproval.get());
    }

    public Boolean evaluationWasCompleted() {
        return approvers.wasCompleted();
    }

    //Getters and Setters

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Approvers getApprovers() {
        return approvers;
    }

    public void setApprovers(Approvers persons) {
        this.approvers = persons;
    }



}
