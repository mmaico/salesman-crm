package br.com.kproj.salesman.administration.approval_negotiation.domain.model.requester;

import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.RequestApproval;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.RequestApprovalRepository;
import br.com.kproj.salesman.administration.approval_negotiation.domain.services.ApproversAvailableService;
import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import static br.com.kproj.salesman.infrastructure.helpers.AutowireHelper.autowire;

@Model
public class Requester extends ModelIdentifiable {

    private Long id;

    @Autowired
    private RequestApprovalRepository repository;

    public Requester() {
        autowire(this);
    }

    public Requester(Long id) {
        this();
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApproversAvailableService request(RequestApproval approval) {
        return approvers -> {
            approval.startWithApprovers(approvers);
            return repository.save(approval);
        };
    }

    public static Requester requester() {
        return new Requester();
    }
}
