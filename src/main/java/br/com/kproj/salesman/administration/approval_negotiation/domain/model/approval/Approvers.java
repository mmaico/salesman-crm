package br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval;


import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.Approver;
import br.com.kproj.salesman.infrastructure.model.CollectionBehavior;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class Approvers extends CollectionBehavior<PersonApproval> {

    private final List<PersonApproval> approvers;

    public Approvers() {
        this.approvers = Lists.newArrayList();
    }

    public Approvers(List<PersonApproval> approvers) {
        this.approvers = approvers;
    }

    public Boolean needsToBeEvaluationBy(Approver approver) {
        Optional<PersonApproval> approval = approvers.stream()
                .filter(item -> item.getApprover().equals(approver) && PersonApproval.Status.WAITING.equals(item.getStatus()))
                .findFirst();

        return approval.isPresent();
    }

    public Optional<PersonApproval> getOne(Approver approver) {
        return approvers.stream()
                .filter(item -> item.getApprover().equals(approver)).findFirst();
    }

    public Boolean wasCompleted() {
        return approvers.stream()
                .filter(item -> !PersonApproval.Status.APPROVED.equals(item.getStatus())).count() == 0;
    }

    @Override
    public Collection<PersonApproval> getCollection() {
        return approvers;
    }
}
