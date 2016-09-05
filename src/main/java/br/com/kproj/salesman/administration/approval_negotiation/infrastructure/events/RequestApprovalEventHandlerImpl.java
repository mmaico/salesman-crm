package br.com.kproj.salesman.administration.approval_negotiation.infrastructure.events;


import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.RequestApproval;
import br.com.kproj.salesman.administration.approval_negotiation.domain.model.approval.RequestApprovalEventHandler;
import br.com.kproj.salesman.infrastructure.events.NewRequestApprovalMessage;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestApprovalEventHandlerImpl implements RequestApprovalEventHandler {

    @Autowired
    private EventBus eventBus;


    @Override
    public void newRequestApproval(RequestApproval message) {
        eventBus.post(NewRequestApprovalMessage.createMessage(message));
    }
}
