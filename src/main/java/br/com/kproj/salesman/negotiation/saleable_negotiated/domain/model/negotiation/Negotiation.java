package br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiation;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;

@Model
public class Negotiation extends ModelIdentifiable {

    private Long id;


    public Negotiation() {}

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
