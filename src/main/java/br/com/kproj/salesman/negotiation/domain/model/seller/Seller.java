package br.com.kproj.salesman.negotiation.domain.model.seller;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation.domain.model.negotiation.NegotiationChangeTemperature;
import br.com.kproj.salesman.negotiation.domain.model.negotiation.NegotiationRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class Seller extends ModelIdentifiable {

    private Long id;

    @Autowired
    private NegotiationRepository repository;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void changeTemperatureFrom(NegotiationChangeTemperature negotiationToChange) {
        Negotiation negotiation = repository.findOne(negotiationToChange.getNegotiation().getId()).get();

        negotiation.setTemperature(negotiationToChange.getNewTemperature());
    }
}
