package br.com.kproj.salesman.notifications2.approval.domain.model.negotiation;

import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.notifications2.approval.domain.model.notification.NotificationRepository;
import br.com.kproj.salesman.notifications2.approval.domain.model.notification.Receivers;
import br.com.kproj.salesman.notifications2.approval.domain.model.notification.RequestNotification;
import com.trex.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import static br.com.kproj.salesman.infrastructure.helpers.AutowireHelper.autowire;
import static br.com.kproj.salesman.notifications2.approval.domain.model.notification.RequestNotification.createRequest;

@Model
public class Negotiation extends ModelIdentifiable {

    private Long id;

    @Autowired
    private NotificationRepository repository;


    public Negotiation() {
        autowire(this);
    }
    public Negotiation(Long id) {
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

    public Negotiation inApproval() {
        return this;
    }

    public Negotiation requestNotifications() {
        return this;
    }

    public void to(Receivers receivers) {
        repository.register(createRequest(this, receivers));
    }
}
