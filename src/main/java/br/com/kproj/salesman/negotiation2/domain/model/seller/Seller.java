package br.com.kproj.salesman.negotiation2.domain.model.seller;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.negotiation2.domain.model.negotiation.NegotiationToChangeTemperature;


public class Seller extends ModelIdentifiable {

    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void changeTemperatureFrom(NegotiationToChangeTemperature changeTemperature) {

    }
}
