package br.com.kproj.salesman.administration.approval_negotiation.application;

import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver.Approver;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;

import java.util.Collection;
import java.util.Optional;

public interface ApproverFacade extends ModelFacade<Approver> {


    Approver makeAvailable(Approver approver);

    Approver makeAnavailable(Approver approver);

    Collection<Approver> getApprovers();

}
