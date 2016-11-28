package br.com.kproj.salesman.negotiation.negotiation.domain.model.seller;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation.NegotiationRepository;
import br.com.kproj.salesman.negotiation.negotiation.domain.model.negotiation.Temperature;
import br.com.kproj.salesman.negotiation.negotiation.domain.service.NegotiationChangeTemperatureService;
import br.com.kproj.salesman.negotiation.negotiation.domain.service.SaveNegotiationService;
import org.springframework.beans.factory.annotation.Autowired;

import static br.com.kproj.salesman.infrastructure.helpers.AutowireHelper.autowire;


public class Seller extends ModelIdentifiable {

    private Long id;

    @Autowired
    private NegotiationRepository repository;

    public Seller() {
        autowire(this);
    }
    public Seller(Long id) {
        this();
        this.id = id;
    }

//    public NegotiationChangeTemperatureService changeTemperature(Temperature newTemperature) {
//        return (negotiation -> negotiation.changeTemperatureFor(newTemperature));
//    }

    public SaveNegotiationService save(Negotiation negotiation) {
        return (temperature -> {
                negotiation.setTemperature(temperature);
                return repository.save(negotiation);
        });
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



}
