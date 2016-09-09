package br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval;


import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.Approver;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.requester.Requester;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.google.common.collect.Lists;
import com.trex.shared.annotations.Attribute;
import com.trex.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
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

    private List<PersonApproval> approvers = Lists.newArrayList();

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

        Optional<PersonApproval> approval = approvers.stream()
                .filter(item -> item.getApprover().equals(approver) && PersonApproval.Status.WAITING.equals(item.getStatus()))
                .findFirst();

        return !approval.isPresent();
    }

    public void makeEvaluation(Approver approver, PersonApproval.Status status) {
        Optional<PersonApproval> personApproval = approvers.stream()
                    .filter(item -> item.getApprover().equals(approver)).findFirst();

        personApproval.get().setStatus(status);
        personRepository.save(personApproval.get());
    }

    public Boolean evaluationWasCompleted() {
        return approvers.stream()
                .filter(item -> !PersonApproval.Status.APPROVED.equals(item.getStatus())).count() > 0;
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

    public List<PersonApproval> getApprovers() {
        return approvers;
    }

    public void setApprovers(List<PersonApproval> persons) {
        this.approvers = persons;
    }



}
