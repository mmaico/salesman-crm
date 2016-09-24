package br.com.kproj.salesman.negotiation.domain.model.seller;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation.domain.model.negotiation.NegotiationRepository;
import br.com.kproj.salesman.negotiation.domain.model.negotiation.Temperature;
import org.springframework.beans.factory.annotation.Autowired;

import static br.com.kproj.salesman.infrastructure.helpers.AutowireHelper.autowire;


public class Seller extends ModelIdentifiable {

    private Long id;

    @Autowired
    private NegotiationRepository repository;

    public Seller() {
        autowire(this);
    }

    public Seller changeTemperature(Temperature newTemperature) {
        this.context.add(Temperature.class, newTemperature);
        return this;
    }

    public void from(Negotiation negotiation) {
        Temperature newTemperature = (Temperature) this.context.get(Temperature.class);
        negotiation.changeTemperatureFor(newTemperature);
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
