package br.com.kproj.salesman.auditing.negotiation.domain.model.user;


import br.com.kproj.salesman.auditing.negotiation.domain.model.audit.Auditing;
import br.com.kproj.salesman.auditing.negotiation.domain.model.audit.AuditingRepository;
import br.com.kproj.salesman.auditing.negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;

import java.util.Optional;

import static br.com.kproj.salesman.auditing.negotiation.domain.model.audit.AuditingBuilder.createNewAudit;

@Model
public class User extends ModelIdentifiable {

    private Long id;

    private AuditingRepository repository;

    public User() {
        AutowireHelper.autowire(this);
    }
    public User(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User changed(Negotiation negotiation) {
        this.context.add(Negotiation.class, negotiation);
        return this;
    }

    public Optional<Auditing> andTheNewVersionIs(String data) {
        Negotiation negotiation = (Negotiation) this.context.get(Negotiation.class);
        Auditing auditing = createNewAudit().currentDate()
                .withData(data)
                .withNegotiation(negotiation)
                .withUser(this).build();

        return repository.save(auditing);
    }
}
