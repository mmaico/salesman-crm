package br.com.kproj.salesman.notifications2.approval.domain.model.notification;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.notifications2.approval.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.notifications2.approval.domain.model.user.Receiver;
import com.trex.shared.annotations.Model;

import java.util.Date;

@Model
public class Notification extends ModelIdentifiable {

    private Long id;
    private Date creation;
    private Receiver receiver;
    private Negotiation negotiation;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public Negotiation getNegotiation() {
        return negotiation;
    }

    public void setNegotiation(Negotiation negotiation) {
        this.negotiation = negotiation;
    }
}
