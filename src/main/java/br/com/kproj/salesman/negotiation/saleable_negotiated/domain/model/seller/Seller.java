package br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.seller;

import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.Negotiated;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.NegotiatedRepository;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.services.NegotiatedToNegotiationService;
import com.trex.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

@Model
public class Seller extends ModelIdentifiable {

    private Long id;

    @Autowired
    private NegotiatedRepository repository;

    public Seller() {
        AutowireHelper.autowire(this);
    }

    public NegotiatedToNegotiationService save(Negotiated negotiated) {
        return (negotiation -> {
            negotiated.setNegotiation(negotiation);
            return repository.save(negotiated);
        });
    }

    public Negotiated update(Negotiated negotiated) {
        return repository.update(negotiated);
    }


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public static Seller seller() {
        return new Seller();
    }
}
